package cat.itacademy.M13.Nivell1.Dades;

import java.util.ArrayList;
import java.util.List;

import cat.itacademy.M13.Nivell1.model.Empleat;
import cat.itacademy.M13.Nivell1.Dades.Feines;

public class BaseDades {
	
	private List<Empleat> empleats = new ArrayList<Empleat>();

	    
	public BaseDades() {
		                   //(int id, String name, String job, double salary)
		empleats.add(new Empleat(1,"Joan",Feines.PROFESSOR.name(),new Salari(Feines.PROFESSOR).getSou()));
		empleats.add(new Empleat(2,"Emma",Feines.DENTISTA.name(),new Salari(Feines.DENTISTA).getSou()));
		empleats.add(new Empleat(3,"Mia",Feines.MECÀNIC.name(),new Salari(Feines.MECÀNIC).getSou()));
		empleats.add(new Empleat(4,"Ona",Feines.TÈCNIC.name(),new Salari(Feines.TÈCNIC).getSou()));
		
		empleats.add(new Empleat(5,"Pau",Feines.TÈCNIC.name(),new Salari(Feines.TÈCNIC).getSou()));
		empleats.add(new Empleat(6,"Lucia",Feines.OPERARI.name(),new Salari(Feines.OPERARI).getSou()));
		empleats.add(new Empleat(7,"Paula",Feines.MECÀNIC.name(),new Salari(Feines.MECÀNIC).getSou()));
		empleats.add(new Empleat(8,"Laia",Feines.TÈCNIC.name(),new Salari(Feines.TÈCNIC).getSou()));
		
		empleats.add(new Empleat(9,"Lucas",Feines.GRANGER.name(),new Salari(Feines.GRANGER).getSou()));
		empleats.add(new Empleat(10,"Hugo",Feines.DENTISTA.name(),new Salari(Feines.DENTISTA).getSou()));
		empleats.add(new Empleat(11,"Biel",Feines.MECÀNIC.name(),new Salari(Feines.MECÀNIC).getSou()));
		empleats.add(new Empleat(12,"Sofía",Feines.TÈCNIC.name(),new Salari(Feines.TÈCNIC).getSou()));
		
		empleats.add(new Empleat(13,"Sara",Feines.PROFESSOR.name(),new Salari(Feines.PROFESSOR).getSou()));
		empleats.add(new Empleat(14,"Jana",Feines.DENTISTA.name(),new Salari(Feines.DENTISTA).getSou()));
		empleats.add(new Empleat(15,"Enzo",Feines.GRANGER.name(),new Salari(Feines.GRANGER).getSou()));
		empleats.add(new Empleat(16,"Alba",Feines.TÈCNIC.name(),new Salari(Feines.TÈCNIC).getSou()));
		
		empleats.add(new Empleat(17,"Daniel",Feines.CONDUCTOR.name(),new Salari(Feines.CONDUCTOR).getSou()));
		empleats.add(new Empleat(18,"Roc",Feines.TÈCNIC.name(),new Salari(Feines.TÈCNIC).getSou()));
		empleats.add(new Empleat(19,"Gala",Feines.MECÀNIC.name(),new Salari(Feines.MECÀNIC).getSou()));
		empleats.add(new Empleat(20,"Lara",Feines.TÈCNIC.name(),new Salari(Feines.TÈCNIC).getSou()));
	}
	
	public void addEmpleat(Empleat emp) {
		this.empleats.add(emp);
	}
	
	
	// getters & setters
	public List<Empleat> getEmpleats() {
		return empleats;
	}

	public void setEmpleats(List<Empleat> empleats) {
		this.empleats = empleats;
	}	
	

	
	
}
