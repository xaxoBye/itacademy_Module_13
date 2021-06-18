package cat.barcelonactiva.itacademy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cat.barcelonactiva.itacademy.Exception.ModelNotFoundException;
import cat.barcelonactiva.itacademy.model.entity.Empleat;
import cat.barcelonactiva.itacademy.model.entity.Feines;
import cat.barcelonactiva.itacademy.models.entity.services.IEmpleatService;
//import cat.ajax.ejercicio.models.ProductModel;
//import cat.itacademy.M13.Nivell1.Exception.ModelNotFoundException;
import cat.barcelonactiva.itacademy.repository.FeinaSalari;
import cat.barcelonactiva.itacademy.repository.Salari;


@RestController
@RequestMapping("api/ajaxrest")
public class AjaxRestController {
	
	@Autowired
	private IEmpleatService empleatServicio;
	

	// LLISTA UN EMPLEAT
	// GET
	// el num 5 es el empleatId
	// http://localhost:8080/api/ajaxrest/5
	@RequestMapping(value="{empleatId}")
	public ResponseEntity<Empleat> getByIdEmpleat(@PathVariable("empleatId") int id){
		
		// listado de todos los empleados
		List<Empleat> empleatsLlistat = empleatServicio.listarTodos();
		
	
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
	
	
	// LLISTA TOTS ELS EMPLEATS
	// GET
	// http://localhost:8080/api/ajaxrest/
	@GetMapping
	public ResponseEntity<List<Empleat>> getListaEmpleats(){
		// listado de todos los empleados
		List<Empleat> empleatsLlistat = empleatServicio.listarTodos();
		
		if(empleatsLlistat == null) {
			throw new ModelNotFoundException("NO TROBA CAP EMPLEAT");
		}		
	
		return new ResponseEntity<List<Empleat>>(empleatsLlistat, HttpStatus.OK);
	}
	
	
	
	// LLISTA TOTS ELS TREBALLS
	// GET
	// http://localhost:8080/api/ajaxrest/feines
	@GetMapping("/feines")
	public ResponseEntity<List<FeinaSalari>> getListaTreballs(){
		// listado de todos els treballs
		List<FeinaSalari> jobs = new ArrayList<FeinaSalari>();
		
		// crear una lista de objeto con nombre empleo y su salario
		for(cat.barcelonactiva.itacademy.model.entity.Feines f: Feines.values()) {			
			jobs.add(new FeinaSalari(f.toString() , new Salari(f).getSou()));			
		}		
		
		
		if(jobs.size() < 1) {
			throw new ModelNotFoundException("NO TROBA CAP TREBALL");
		}
		
		return new ResponseEntity<List<FeinaSalari>>(jobs, HttpStatus.OK);
	}
	
	
	// POSTMAN
	// CANVIA UN EMPLEAT
	// metodo = PUT
	// http://localhost:8080/api/ajaxrest/
	// En Postman (body)
	//   {
	//     "idEmpleat" : 2,
    //     "nom": "Maria",
    //     "feina": "GRANGER"
    //    }
	@PutMapping
	public ResponseEntity<Empleat> updateEmpleat(@Validated @RequestBody Empleat empleat){   //  (@Validated @RequestBody Empleat empleat)
		// listado de todos los empleados
		List<Empleat> empleatsLlistat = empleatServicio.listarTodos();
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
		
		
		for(Empleat emp: empleatsLlistat) {
			
			if(emp.equals(empleat)) {
				emp.setNom(empleat.getNom());
				emp.setFeina(empleat.getFeina());
				emp.setSalari(salariNou.getSou());				
				empleatServicio.guardar(emp);

				return new ResponseEntity<Empleat>(emp, HttpStatus.OK);
			}
		}
		return (ResponseEntity<Empleat>) ResponseEntity.noContent().build().notFound();	
	}
	

	// POSTMAN
	// NOU EMPLEAT
	// metodo = POST
	// http://localhost:8080/api/ajaxrest/
	// En Postman (body)
	//   {
	//     "idEmpleat" : 2,
    //     "nom": "Maria",
    //     "feina": "GRANGER"
    //    }
	@PostMapping("/save")
	public ResponseEntity<Empleat> guardarEmpleat(@Validated @RequestBody Empleat empleat){  

		Empleat nouEmpleat = new Empleat();
	
		nouEmpleat.setNom(empleat.getNom());
		nouEmpleat.setFeina(empleat.getFeina());
		nouEmpleat.setSalari(empleat.getSalari());
				
		empleatServicio.guardar(nouEmpleat);
		
		return new ResponseEntity<Empleat>(nouEmpleat, HttpStatus.OK);	
	}	

	
	// POSTMAN
	// ESBORRA UN EMPLEAT
	//metodo = DELETE
	//http://localhost:8080/api/ajaxrest/delete/2
	// @DeleteMapping("/delete/{id}") -> como lo estoy pasando los parametros por la URL no  funciona @DeleteMapping
	@RequestMapping("/delete/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Long idEmpleat){
		                                 
		empleatServicio.eliminar(idEmpleat);
		
		System.out.println("Registre " + idEmpleat + " esborrat correctament");
		for(Empleat emp: empleatServicio.listarTodos()) {
			System.out.println(emp);
		}
		
		List<Empleat> empleatsLlistat= empleatServicio.listarTodos();
		String jsonEmpleats = new Gson().toJson(empleatsLlistat);
		
		System.out.println(jsonEmpleats.toString());

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// Ordenar listados
	@GetMapping("/order/{orden}")
	public ResponseEntity<List<Empleat>> ordenar(@PathVariable("orden") String parametre ,Model model) {
		// listado de todos los empleados
		List<Empleat> empleatsLlistat= empleatServicio.listarTodos();
		
		
		if(empleatsLlistat == null) {
			throw new ModelNotFoundException("NO TROBA CAP EMPLEAT");
		}		
	
		
		
		
		
		
		String ordenado = parametre.toUpperCase();
		
	       switch (ordenado) 
	        {
	            case "ID":  	empleatsLlistat.sort((Empleat emp1, Empleat emp2)->emp1.getIdEmpleat().compareTo(emp2.getIdEmpleat()));
	                     		break;
	            case "NOM":  	empleatsLlistat.sort((Empleat emp1, Empleat emp2)->emp1.getNom().toUpperCase().compareTo(emp2.getNom().toUpperCase()));
	                     		break;
	            case "FEINA":   empleatsLlistat.sort((Empleat emp1, Empleat emp2)->emp1.getFeina().toUpperCase().compareTo(emp2.getFeina().toUpperCase()));
	            				break;
	            case "SALARI":  empleatsLlistat.sort((Empleat emp1, Empleat emp2)->(Double.compare(emp1.getSalari(), emp2.getSalari())));
	                     		break;
	            default: 		empleatsLlistat.sort((Empleat emp1, Empleat emp2)->emp1.getIdEmpleat().compareTo(emp2.getIdEmpleat()));
	        }  
		
		
	       return new ResponseEntity<List<Empleat>>(empleatsLlistat, HttpStatus.OK);
			
		// model.addAttribute("empleats",empleatsLlistat);
		
		
	}


}
