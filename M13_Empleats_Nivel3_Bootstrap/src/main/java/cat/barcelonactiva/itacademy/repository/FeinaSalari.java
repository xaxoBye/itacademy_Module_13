package cat.barcelonactiva.itacademy.repository;

public class FeinaSalari {
	private String nomFeina;
	private double salari;
	
	public FeinaSalari(String feina, double sou) {
		this.nomFeina = feina;
		this.salari = sou;
	}

	public String getNomFeina() {
		return nomFeina;
	}

	public void setNomFeina(String nomFeina) {
		this.nomFeina = nomFeina;
	}

	public double getSalari() {
		return salari;
	}

	public void setSalari(double salari) {
		this.salari = salari;
	}
	
	// getters & setters
	
	
}
