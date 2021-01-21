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
	    
	    /**Contato novoContato2 = new Contato("Pedro Franco Marques","Logistica",new Celular("Oi","35","988653344"),
	    		new Email("pedromarques@gmail.com"));**/
	    
	    List<Compromisso >compromissos =cd.findAll();
	    
	    //cd.update(novoContato2);
	    
	    //Compromisso novoCompromisso = new Compromisso("Festa da empresa","2021-01-25","09:30","Rua do treino 100","Perto de algum lugar","curso de treinamento");
	    
	    //cd.delete(novoCompromisso);
	    
	   for(Compromisso i:compromissos) {
		   
		System.out.println(i);
		
	    }
	    
		System.out.println("Deu certo!!!");
	
		//Email email= new Email("nandodesouza@gmail.com");
		
		//System.out.println(email.getEmail());
		
		
		
		launch();
		
	}

}
