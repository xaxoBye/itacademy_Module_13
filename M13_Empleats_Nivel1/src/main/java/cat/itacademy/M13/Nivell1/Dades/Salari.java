package cat.itacademy.M13.Nivell1.Dades;

import cat.itacademy.M13.Nivell1.Dades.Feines;

public class Salari {
	private double sou;
	
	public Salari(Feines job) {
		
		this.sou = this.getSalari(job);
		
	}
	
	private double getSalari(Feines job)
	{
        String feina = job.name();

  
        switch (feina) 
        {
            case "CONDUCTOR":this.sou = 1800;
            				 break;
            case "PROFESSOR":this.sou = 2800;
            				 break;
            case "DENTISTA": this.sou = 2000;
            			     break;
            case "TÈCNIC":   this.sou = 2500;
                     		 break;
            case "GRANGER":  this.sou = 3800;
            				 break;
            case "OPERARI":  this.sou = 1200;
                     		 break;
            case "MECÀNIC":  this.sou = 1300;
                     		 break;
            default: 		 this.sou = 900;
            				 break;
        }
		
        return this.sou;
	}

	public double getSou() {
		return sou;
	}
	
	
}
