package cat.barcelonactiva.itacademy.controller.exception;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class renderErrorPage {
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public @ResponseBody String getResponse() {
		return "404";
	}

}
