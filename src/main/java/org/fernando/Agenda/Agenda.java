package org.fernando.Agenda;

import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import model.entities.Compromisso;
import model.entitiesDAO.CompromissoDAO;
import model.entitiesDAO.UtilDAO;

public class Agenda extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

	}

	public static void main(String[] args) {

				
		CompromissoDAO cd = new CompromissoDAO(UtilDAO.getConnection());
	    
		//List<Compromisso> meusComp =cd.findByDateTime("2021-09-09","09:00");
	    
	    //System.out.println(meusComp);
	    
	    Compromisso velhoCompromisso = new Compromisso("Festa na casa do João","2021-02-21","20:30:00","Rua das andorinhas, 300","Perto do Epa Pampulha","Levar bebida");
	    Compromisso novoCompromisso = new Compromisso("Festa na casa do João","2021-02-23","20:30:00","Rua das andorinhas, 300","Perto do Epa Pampulha","Levar bebida");

	    
	    //cd.insert(velhoCompromisso);
	    
	    cd.updateDiaHora(novoCompromisso, velhoCompromisso);
	    
	    List<Compromisso> compromissos =cd.findAll();
	    
	    //cd.update(novoContato2);
	    
	    //Compromisso novoCompromisso = new Compromisso("Festa da empresa","2021-01-25","09:30:00","Rua do treino 100","Perto de algum lugar","curso de treinamento");
	    
	    //cd.delete(novoCompromisso);
	    
	   for(Compromisso i:compromissos) {
		   
		System.out.println(i);
		
	    }
	    
	    System.out.println(cd.findByDateTime(novoCompromisso.getData().toString(),novoCompromisso.getHorario().toString()));
		System.out.println("Deu certo!!!");
	
		//Email email= new Email("nandodesouza@gmail.com");
		
		//System.out.println(email.getEmail());
		
		
		
		launch();
		
	}

}
