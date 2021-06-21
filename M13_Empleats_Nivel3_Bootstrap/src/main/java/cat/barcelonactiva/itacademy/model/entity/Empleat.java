package cat.barcelonactiva.itacademy.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="empleat")
public class Empleat implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_empleat")
	private Long idEmpleat;
	
	@Size(max = 150, message = "Nom hauria de ser menor a 150 caràcters")
	@Column(name="nom", nullable=false, length = 150)
	private String nom;
	
	@Size(max = 15, message = "Feina hauria de ser menor a 15 caràcters")
	@Column(name="feina", nullable=false, length = 15)
	private String feina;
	
	@Column(name="salari", nullable=false)
	private double salari;
	
	
	@Override
	public String toString() {
		return "Empleat [idEmpleat=" + idEmpleat + ", nom=" + nom + ", feina=" + feina + ", salari=" + salari + "]";
	}

	// getters & setters
	public Long getIdEmpleat() {
		return idEmpleat;
	}
	public void setIdEmpleat(Long idEmpleat) {
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


	
	// Comparacion de obj
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
	
}