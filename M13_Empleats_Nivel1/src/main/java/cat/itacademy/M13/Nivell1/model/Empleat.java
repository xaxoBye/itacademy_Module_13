package cat.itacademy.M13.Nivell1.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Empleat {
	private Integer idEmpleat;
	
	@NotNull(message ="Nom no ha d'estar buit")
	@Size(min=3, message = "Nom mínim 3 caràcters")
	private String nom;
	
	@NotNull(message ="Feina no ha d'estar buit")
	private String feina;
	
	private double salari;
	
	public Empleat(int id, String name, String job, double salary) {
		this.idEmpleat = id;
		this.nom = name;
		this.feina = job;
		this.salari = salary;		
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmpleat == null) ? 0 : idEmpleat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleat other = (Empleat) obj;
		if (idEmpleat == null) {
			if (other.idEmpleat != null)
				return false;
		} else if (!idEmpleat.equals(other.idEmpleat))
			return false;
		return true;
	}





	// getters & setters
	public Integer getIdEmpleat() {
		return idEmpleat;
	}
	public void setIdEmpleat(Integer idEmpleat) {
		this.idEmpleat = idEmpleat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getFeina() {
		return feina;
	}
	public void setFeina(String feina) {
		this.feina = feina;
	}
	public double getSalari() {
		return salari;
	}
	public void setSalari(double salari) {
		this.salari = salari;
	}
	

}

