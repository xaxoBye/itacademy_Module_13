package cat.barcelonactiva.itacademy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cat.barcelonactiva.itacademy.model.entity.Empleat;
import cat.barcelonactiva.itacademy.model.entity.Feines;
import cat.barcelonactiva.itacademy.models.entity.services.IEmpleatService;
import cat.barcelonactiva.itacademy.repository.FeinaSalari;
import cat.barcelonactiva.itacademy.repository.Salari;


//@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("views/employers")
public class EmpleatController {
	
	@Autowired
	private IEmpleatService empleatServicio;
	
	List<FeinaSalari> jobs = new ArrayList<FeinaSalari>();

	@GetMapping("/")
	public String listarEmpleats(Model model) {	
		List<Empleat> llistatEmpleat = empleatServicio.listarTodos();
		
		
		Empleat empl = new Empleat();
		
//		empl.setIdEmpleat(8L);

		
		// crear una lista de objeto con nombre empleo y su salario
		for(cat.barcelonactiva.itacademy.model.entity.Feines f: Feines.values()) {			
			jobs.add(new FeinaSalari(f.toString() , new Salari(f).getSou()));			
		}
		
		model.addAttribute("jobs",jobs);
		
		model.addAttribute("nouId", empl.getIdEmpleat());
		model.addAttribute("tituloFrm", "Formulari: Nou empleat");
		model.addAttribute("empleat", empl);
		
		model.addAttribute("titulo", "Llistat d'empleats");
		model.addAttribute("empleatsLst", llistatEmpleat);
		
		return "/views/empleats/listar";
	}
	
	@GetMapping("/order/{orden}")
	public String ordenar(@PathVariable("orden") String parametre ,Model model) {
		// listado de todos los empleados
		List<Empleat> llistatEmpleat = empleatServicio.listarTodos();
		
		String ordenado = parametre.toUpperCase();
		
	       switch (ordenado) 
	        {
	            case "ID":  	llistatEmpleat.sort((Empleat emp1, Empleat emp2)->emp1.getIdEmpleat().compareTo(emp2.getIdEmpleat()));
	                     		break;
	            case "NOM":  	llistatEmpleat.sort((Empleat emp1, Empleat emp2)->emp1.getNom().compareTo(emp2.getNom()));
	                     		break;
	            case "FEINA":   llistatEmpleat.sort((Empleat emp1, Empleat emp2)->emp1.getFeina().compareTo(emp2.getFeina()));
	            				break;
	            case "SALARI":  llistatEmpleat.sort((Empleat emp1, Empleat emp2)->(Double.compare(emp1.getSalari(), emp2.getSalari())));
	                     		break;
	            default: 		llistatEmpleat.sort((Empleat emp1, Empleat emp2)->emp1.getIdEmpleat().compareTo(emp2.getIdEmpleat()));
	        }  
	       
			Empleat empl = new Empleat();
			

			
			// crear una lista de objeto con nombre empleo y su salario
			for(cat.barcelonactiva.itacademy.model.entity.Feines f: Feines.values()) {			
				jobs.add(new FeinaSalari(f.toString() , new Salari(f).getSou()));			
			}
			
			model.addAttribute("jobs",jobs);
			
			model.addAttribute("nouId", empl.getIdEmpleat());
			model.addAttribute("tituloFrm", "Formulari: Nou empleat");
			model.addAttribute("empleat", empl);
			
			model.addAttribute("titulo", "Llistat d'empleats");
			model.addAttribute("empleatsLst", llistatEmpleat);	       
		
		

			
		model.addAttribute("empleats",llistatEmpleat);
		
		return "/views/empleats/listar";
	}
	


}
