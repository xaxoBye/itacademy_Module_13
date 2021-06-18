package cat.itacademy.M13.Nivell1.Exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	
	private LocalDateTime data;
	private String missatge;
	private String detalls;
	
	public ExceptionResponse() {}
	
	public ExceptionResponse(LocalDateTime data, String missatge, String detalls) {
		super();
		this.data = data;
		this.missatge = missatge;
		this.detalls = detalls;
	}

	
	// getters & setters
	public LocalDateTime getdata() {
		return data;
	}

	public void setdata(LocalDateTime data) {
		this.data = data;
	}

	public String getmissatge() {
		return missatge;
	}

	public void setmissatge(String missatge) {
		this.missatge = missatge;
	}

	public String getdetalls() {
		return detalls;
	}

	public void setdetalls(String detalls) {
		this.detalls = detalls;
	}	

}
