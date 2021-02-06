package model.entitiesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Celular;
import model.entities.Contato;
import model.entities.Fixo;
import model.entities.Telefone;

public class TelefoneDAO {

	Connection conn;
	
	TelefoneDAO (Connection conn){
		
		this.conn = conn;
		
	}
	
	
	
	public boolean insert(Telefone tel,Contato contato) {
		
		PreparedStatement ps = null;
		// PreparedStatement psTel = null;

		Telefone tf = findTel(tel);

		if (tf == null) {

			try {

				ps = conn.prepareStatement(
						"INSERT INTO TELEFONE (CODAREA,NUMERO,TELTIPO,CONTATO_CODAREA,CONTATO_TEL) VALUES (?,?,?,?,?)");

				ps.setString(1, tel.getCodArea());
				ps.setString(2,tel.getNumero());
				ps.setInt(3,tel.getTipo().getID());
				ps.setString(4, contato.getTelefone().getCodArea());
				ps.setString(5, contato.getTelefone().getNumero());
				

				int status = ps.executeUpdate();

				System.out.println(status);

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			} finally {

				UtilDAO.closeConnection();

			}

			return true;

		} else {

			return false;
		}
	}


	public boolean update(Telefone tel, Contato contato) {
		
		PreparedStatement ps = null;
		

		try {

			ps = conn.prepareStatement("UPDATE TELEFONE SET CODAREA = ?,NUMERO = ?,TELTIPO = ? WHERE CONTATO_CODAREA = ? AND CONTATO_TEL = ?");

			ps.setString(1,tel.getCodArea());
			ps.setString(2, tel.getNumero());
			ps.setInt(3,tel.getTipo().getID());
			ps.setString(4, contato.getTelefone().getCodArea());
			ps.setString(5,contato.getTelefone().getNumero());
						
			ps.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			UtilDAO.closeConnection();

		}

		return true;
	}

	
	public List<Telefone> findAll() {
		
		PreparedStatement ps = null;
		ResultSet rsComp = null;
		List<Telefone> telefone= new ArrayList<>();

		String query = "SELECT t.CODAREA,t.NUMERO ,tt.TIPOTELEFONE FROM TELEFONE AS t INNER JOIN  TIPOTELEFONE "
				+ "AS tt ON t.TELTIPO = tt.TIPO_ID ";

		try {

			ps = conn.prepareStatement(query);			

			rsComp = ps.executeQuery();
			

			while (rsComp.next()) {

				String codArea = rsComp.getString("CODAREA");
				String numero = rsComp.getString("NUMERO");
				String tipoTel = rsComp.getString("TIPOTELEFONE");			
				
				if(tipoTel.equals("Celular")) {
					
					telefone.add( new Celular (codArea,numero));	
					
				}else {
					
					telefone.add(new Fixo (codArea,numero));	
					
				}
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			UtilDAO.closeResultSet(rsComp);
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return telefone;	
	}
	
	public Telefone findTel(Telefone tel) {
		
		PreparedStatement ps = null;
		ResultSet rsComp = null;
		Telefone telefone=null;

		String query = "SELECT t.CODAREA,t.NUMERO ,tt.TIPOTELEFONE FROM TELEFONE AS t INNER JOIN  TIPOTELEFONE "
				+ "AS tt ON t.TELTIPO = tt.TIPO_ID WHERE  t.CONTATO_CODAREA = ? AND t.CONTATO_TEL = ? ";

		try {

			ps = conn.prepareStatement(query);

			ps.setString(1,tel.getCodArea());
			ps.setString(2,tel.getNumero());

			rsComp = ps.executeQuery();

			while (rsComp.next()) {

				String codArea = rsComp.getString("CODAREA");
				String numero = rsComp.getString("NUMERO");
				String tipoTel = rsComp.getString("TIPOTELEFONE");			
				
				if(tipoTel.equals("Celular")) {
					
					telefone = new Celular (codArea,numero);	
					
				}else {
					
					telefone = new Fixo (codArea,numero);	
					
				}
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			UtilDAO.closeResultSet(rsComp);
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return telefone;	
		
		
	}
	
	
	public List<Telefone> TelByContato(Contato contato) {
		
		PreparedStatement ps = null;
		ResultSet rsComp = null;
		List<Telefone>telefone = new ArrayList<>();

		String query = "SELECT t.CODAREA,t.NUMERO ,tt.TIPOTELEFONE FROM TELEFONE AS t INNER JOIN  TIPOTELEFONE"
				+ " AS tt ON t.TELTIPO = tt.TIPO_ID WHERE  t.CONTATO_CODAREA = ? AND t.CONTATO_TEL = ? ";

		try {

			ps = conn.prepareStatement(query);

			ps.setString(1,contato.getTelefone().getCodArea());
			ps.setString(2,contato.getTelefone().getNumero());

			rsComp = ps.executeQuery();

			while (rsComp.next()) {

				String codArea = rsComp.getString("CODAREA");
				String numero = rsComp.getString("NUMERO");
				String tipoTel = rsComp.getString("TIPOTELEFONE");			
				
				if(tipoTel.equals("Celular")) {
					
					telefone.add(new Celular (codArea,numero));	
					
				}else {
					
					telefone.add(new Fixo (codArea,numero));	
					
				}
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			UtilDAO.closeResultSet(rsComp);
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return telefone;
		
	}

	
	public boolean delete(Telefone obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
