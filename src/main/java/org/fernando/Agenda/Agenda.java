package org.fernando.Agenda;

import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import model.entities.Celular;
import model.entities.Contato;
import model.entities.Email;
import model.entitiesDAO.ContatoDAO;
import model.entitiesDAO.UtilDAO;

public class Agenda extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

	}

	public static void main(String[] args) {

		//Connection conn = UtilDAO.getConnection();
		
		ContatoDAO cd = new ContatoDAO(UtilDAO.getConnection());
	    //List<Contato>cliente =cd.findByname("fernando a. souza");
	    
	    Contato novoContato2 = new Contato("Maria José Freitas","Marketing",new Celular("Oi","Celular","31","988653345"),
	    		new Email("joseramos@gmail.com"));
	    
	    //List<Contato >contatos =cd.findByTel(new Celular("Vivo","Celular","31","982142713"));
	    
	    cd.insert(novoContato2);
	    
	    
	   /*8for(Contato i:contatos) {
		System.out.println(i.toString());
		
	    }**/
	    
		System.out.println("Deu certo!!!");
	
		//Email email= new Email("nandodesouza@gmail.com");
		
		//System.out.println(email.getEmail());
		
		
		
		launch();
		
	}

}
