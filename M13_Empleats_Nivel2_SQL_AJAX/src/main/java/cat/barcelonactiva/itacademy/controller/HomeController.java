package cat.barcelonactiva.itacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

//@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class HomeController {
	
	@GetMapping({"/","/index","/home"})
	public String index() {
		
		return "home";
	}

}
