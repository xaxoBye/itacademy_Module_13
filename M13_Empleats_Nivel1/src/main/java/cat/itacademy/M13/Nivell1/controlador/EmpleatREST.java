package cat.itacademy.M13.Nivell1.controlador;


import cat.itacademy.M13.Nivell1.Dades.BaseDades;
import cat.itacademy.M13.Nivell1.Dades.Feines;
import cat.itacademy.M13.Nivell1.Dades.Salari;
import cat.itacademy.M13.Nivell1.Exception.ModelNotFoundException;
import cat.itacademy.M13.Nivell1.model.Empleat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/empleats")
public class EmpleatREST {
	
	BaseDades bd = new BaseDades();
	
	// LLISTA TOTS ELS EMPLEATS
	// GET
	// http://localhost:8080/empleats/
	@GetMapping
	public ResponseEntity<List<Empleat>> getListaEmpleats(){
		List<Empleat> empleatsLlistat = bd.getEmpleats();
		
		if(empleatsLlistat == null) {
			throw new ModelNotFoundException("NO TROBA CAP EMPLEAT");
		}		
	
		return new ResponseEntity<List<Empleat>>(empleatsLlistat, HttpStatus.OK);
	}
	
	// LLISTA UN EMPLEAT
	// GET
	// el num 5 es el empleatId
	// http://localhost:8080/empleats/5
	@RequestMapping(value="{empleatId}")
	public ResponseEntity<Empleat> getByIdEmpleat(@PathVariable("empleatId") int id){
		
		
		
		List<Empleat> empleatsLlistat= bd.getEmpleats();
		Empleat empleatSeleccionat= null;
		Optional<Empleat> optionalEmpleat=null;
		
		
		
		for(Empleat emp: empleatsLlistat) {
			if(id == emp.getIdEmpleat()) {
				optionalEmpleat = Optional.of(emp);
			}
		}

		if(optionalEmpleat == null) {
			throw new ModelNotFoundException("ID NO TROBAT " + id + " [getByIdEmpleat]");
		}
		
		empleatSeleccionat =optionalEmpleat.get();
		
		return new ResponseEntity<Empleat>(empleatSeleccionat, HttpStatus.OK);
		
	}
	
	
	// LLISTA EMPLEATS PER FEINA
	// GET
	// http://localhost:8080/empleats/feina/PROFESSOR
	@RequestMapping(value="/feina/{feina}")
	public ResponseEntity<List<Empleat>> getByJobEmpleat(@PathVariable("feina") String job){
		
		List<Empleat> empleatsLlistat= bd.getEmpleats();
		List<Empleat> empleatsPerFeina = new ArrayList<Empleat>();
		
		
		for(Empleat emp: empleatsLlistat) {
			if(job.equalsIgnoreCase(emp.getFeina())) {
				empleatsPerFeina.add(emp);
			}
		}
		
		if(empleatsPerFeina == null || empleatsPerFeina.size() == 0) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(empleatsPerFeina);		
		}		
	}
	
	// POSTMAN
	//CREAR UN EMPLEAT
	//metodo = POST
	//http://localhost:8080/empleats/
	//En Postman (body)
	//   {
    //     "nom": "Maria",
    //     "feina": "TÈCNIC",
    //      "salari": 2500.0
    //    }
	// no necesitamos el idEmpleat porque se genera automáticamente
	@PostMapping
	public ResponseEntity<Empleat> crearEmpleat(@Valid @RequestBody Empleat emp) throws Exception {
		Empleat nouEmpleat = emp;		
		
		List<Empleat> empleatsLlista = bd.getEmpleats();
		
		if(nouEmpleat.getIdEmpleat() == null) {
			nouEmpleat.setIdEmpleat(-1);
		}
			
	    Integer numID = 0;
	    int cnt = 1;	    
	    boolean numBool = true;

		if(nouEmpleat.getIdEmpleat() <= 0) {
			nouEmpleat.setIdEmpleat(bd.getEmpleats().size()+1);
		}
		
		numID = nouEmpleat.getIdEmpleat();		
		
		empleatsLlista.sort((Empleat emp1, Empleat emp2)->emp1.getIdEmpleat().compareTo(emp2.getIdEmpleat()));
		
	    for(Empleat empl: empleatsLlista) {
	    	if (cnt == empl.getIdEmpleat()) {		    		
	    		System.out.print("los numeros son iguales -> empl.getIdEmpleat(): " + empl.getIdEmpleat());
		    	System.out.print("\tDentro -> cnt: " + cnt);
		    	System.out.println("\tDentro -> numID: " + numID);

	    	}else {
		    	System.out.print("empl.getIdEmpleat(): " + empl.getIdEmpleat());
		    	System.out.print("\tcnt: " + cnt);
		    	numID = cnt;
		    	System.out.println("\tnumID: " + numID);	
		    	
		    	numBool=false;
	    		break;
	    	}
	    	cnt++;		    	
	    }
	 
	    if(numBool) numID = cnt;		
			
		nouEmpleat.setIdEmpleat(numID);
			
		bd.addEmpleat(nouEmpleat);
		
		return new ResponseEntity<Empleat>(nouEmpleat, HttpStatus.CREATED);
		//return ResponseEntity.ok(nouEmpleat);	
	}
	
	// POSTMAN
	// ESBORRA UN EMPLEAT
	//metodo = DELETE
	//http://localhost:8080/empleats/2
	@DeleteMapping(value="{empleatId}")
	public ResponseEntity<Void> esborrarEmpleat(@PathVariable("empleatId") Integer idEmpleat){
		List<Empleat> empleatsLlista = bd.getEmpleats();
		boolean empleatEsborrat;
		
		empleatEsborrat = empleatsLlista.removeIf((empleat) -> empleat.getIdEmpleat().equals(idEmpleat));
		
		if(!empleatEsborrat) {
			throw new ModelNotFoundException("ID NO TROBAT " + idEmpleat + " [esborrarEmpleat]");
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
	// POSTMAN
	// CANVIA UN EMPLEAT
	// metodo = PUT
	// http://localhost:8080/empleats/
	// En Postman (body)
	//   {
	//     "idEmpleat" : 2,
    //     "nom": "Maria",
    //     "feina": "GRANGER"
    //    }
	@PutMapping
	public ResponseEntity<Empleat> updateEmpleat(@Valid @RequestBody Empleat empleat){
		List<Empleat> empleatsLlista = bd.getEmpleats();
		Salari salariNou = null;
		
		// averiguar el salario segun su trabajo.
		try {
			salariNou = new Salari(Feines.valueOf(empleat.getFeina()));
		}catch(IllegalArgumentException e) {
			throw new ModelNotFoundException("SALARI NOP TROBAT PER AQUEST TREBALL " + empleat.getFeina() + " [updateEmpleat]");
			
			//System.out.println("No hi ha feina de " + empleat.getFeina());
			//e.printStackTrace();
			//return ResponseEntity.noContent().build();			
		}
		
		for(Empleat emp: empleatsLlista) {
			
			if(emp.equals(empleat)) {
				emp.setNom(empleat.getNom());
				emp.setFeina(empleat.getFeina());
				emp.setSalari(salariNou.getSou());
				bd.setEmpleats(empleatsLlista);
				return new ResponseEntity<Empleat>(emp, HttpStatus.OK);
			}
		}
	
		return (ResponseEntity<Empleat>) ResponseEntity.noContent().build().notFound();	
	}
	
	
	
	
	
	// exemple de com insertar un empleat
	/*
	@RequestMapping(value="unEmpleat", method=RequestMethod.GET)
	public ResponseEntity<Empleat> getEmpleat(){
		Empleat empl = new Empleat(999,"Xavier", Feines.DENTISTA.name(), new Salari(Feines.DENTISTA).getSou());
	
	//	empl.setIdEmpleat(1);
	//	empl.setNom("Cris");
	//	empl.setFeina("Telefonista");
	//	empl.setSalari(1900);
		
		return ResponseEntity.ok(empl);
	}
	
	
	//@GetMapping
	@RequestMapping(value="hola", method= RequestMethod.GET)
	public String hello() {
		return "Hola mundo";
	}
    */
}
