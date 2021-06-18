package cat.itacademy.M13.Nivell1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {
	
	public ModelNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}
