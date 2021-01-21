package model.entitiesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Celular;
import model.entities.Contato;
import model.entities.Email;
import model.entities.Fixo;
import model.entities.Telefone;

public class ContatoDAO implements Dao<Contato> {

	Connection conn;

	public ContatoDAO(Connection conn) {

		this.conn = conn;

	}

	@Override
	public boolean insert(Contato contato) {

		PreparedStatement ps = null;
		// PreparedStatement psTel = null;
		

		try {

			ps = conn.prepareStatement("INSERT INTO CONTATO(NOME,CARGO,CODAREA,TELEFONE,TELTIPO,COMPROMISSOID) VALUES (?,?,?,?,?,?)");

			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getCargo());
			ps.setString(3, contato.getTelefone().get(0).getCodArea());
			ps.setString(4, contato.getTelefone().get(0).getNumero());			
			
			switch (contato.getTelefone().get(0).getTipo()) {

			case CELULAR:
				ps.setInt(5, 2);
				break;
			case FIXO:
				ps.setInt(5, 1);
				break;

			}
			
			ps.setInt(6, contato.getCompromisso());


			ps.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			UtilDAO.closeConnection();

		}

		return true;

	}

	@Override
	public boolean update(Contato obj) {
		
		PreparedStatement ps = null;
				

		try {

			ps = conn.prepareStatement("UPDATE CONTATO SET NOME = ?,CARGO = ?,TELTIPO = ?,COMPROMISSOID=? WHERE CODAREA = ? AND TELEFONE = ?");

			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getCargo());
			ps.setInt(3,obj.getTelefone().get(0).getTipo().getID());
			ps.setInt(4, obj.getCompromisso());
			ps.setString(5, obj.getTelefone().get(0).getCodArea());
			ps.setString(6, obj.getTelefone().get(0).getNumero());

			
			ps.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			UtilDAO.closeConnection();

		}

		return true;
	}

	@Override
	public List<Contato> findAll() {

		List<Contato> contatoList = new ArrayList<>();
		Contato contato = null;
		ResultSet rsAll = null;
		Statement st = null;

		try {
			st = conn.createStatement();
			rsAll = st.executeQuery("SELECT c.NOME ,c.CARGO,c.TELEFONE,c.CODAREA,e.EMAIL,"
					+ "TT.TIPOTELEFONE,c.COMPROMISSOID FROM CONTATO AS c LEFT JOIN EMAIL AS e ON e.CONTATO_CODAREA  = c.CODAREA "
					+ "AND e.CONTATO_TELEFONE  = c.TELEFONE LEFT  JOIN TIPOTELEFONE AS TT ON TT.TIPO_ID = c.TELTIPO,"
					+ "LEFT JOIN COMPROMISSO AS cp ON cp.COMPROMISSO_ID = c.COMPROMISSOID");

			while (rsAll.next()) {

				String nome = rsAll.getString("NOME");
				String cargo = rsAll.getString("CARGO");
				String codAreaP = rsAll.getString("CODAREA");
				String telefoneP = rsAll.getString("TELEFONE");
				int tipoTel = rsAll.getInt("TIPOTELEFONE");
				int compromissoId = rsAll.getInt("COMPROMISSOID");
				String email = rsAll.getString("EMAIL");

				switch (tipoTel) {

				case 2:

					contato = new Contato(nome, cargo, new Celular(codAreaP, telefoneP), new Email(email),compromissoId);

					break;
				case 1:

					contato = new Contato(nome, cargo, new Fixo(codAreaP, telefoneP), new Email(email),compromissoId);
					break;
				}

				contatoList.add(contato);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			UtilDAO.closeConnection();
			UtilDAO.closeStatment(st);
			UtilDAO.closeResultSet(rsAll);

		}
		return contatoList;
	}

	public List<Contato> findByname(String name) {

		PreparedStatement ps = null;
		ResultSet rsName = null;
		Contato contato = null;
		List<Contato> contatobyName = new ArrayList<>();

		String query = "SELECT c.NOME ,c.CARGO,c.CODAREA ,c.TELEFONE,c.COMPROMISSOID,e.EMAIL,TT.TIPOTELEFONE FROM CONTATO AS c LEFT JOIN EMAIL AS e ON e.CONTATO_CODAREA = c.CODAREA AND e.CONTATO_TELEFONE = c.TELEFONE\n"
				+ "LEFT JOIN TIPOTELEFONE AS TT ON TT.TIPO_ID = c.TELTIPO LEFT JOIN COMPROMISSO AS cp ON c.COMPROMISSOID = cp.COMPROMISSO_ID WHERE UPPER(c.NOME) = UPPER(?) ";

		try {

			ps = conn.prepareStatement(query);

			ps.setString(1, name);

			rsName = ps.executeQuery();

			while (rsName.next()) {

				String nome = rsName.getString("NOME");
				String cargo = rsName.getString("CARGO");
				String codArea = rsName.getString("CODAREA");
				String telefone = rsName.getString("TELEFONE");
				int tipoTel = rsName.getInt("TIPOTELEFONE");
				String email = rsName.getString("EMAIL");
				int compromissoId = rsName.getInt("COMPROMISSOID");

				switch (tipoTel) {

				case 2:

					contato = new Contato(nome, cargo, new Celular(codArea, telefone), new Email(email),compromissoId);
					break;
				case 1:

					contato = new Contato(nome, cargo, new Fixo(codArea, telefone), new Email(email),compromissoId);
					break;
				}

				contatobyName.add(contato);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			UtilDAO.closeResultSet(rsName);
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return contatobyName;

	}

	public List<Contato> findByTel(Telefone telefone) {

		PreparedStatement ps = null;
		ResultSet rsTel = null;
		Contato contato = null;
		List<Contato> contatobyTel = new ArrayList<>();

		String query = "SELECT c.NOME ,c.CARGO,c.CODAREA ,c.TELEFONE ,e.EMAIL,TT.TIPOTELEFONE,c.COMPROMISSOID"
				+ " FROM CONTATO AS c LEFT JOIN EMAIL AS e ON e.CONTATO_CODAREA = c.CODAREA AND e.CONTATO_TELEFONE = c.TELEFONE"
				+ " LEFT JOIN TIPOTELEFONE AS TT ON TT.TIPO_ID = c.TELTIPO  WHERE c.CODAREA = ? AND  c.TELEFONE = ? ";

		try {

			ps = conn.prepareStatement(query);

			ps.setString(1, telefone.getCodArea());
			ps.setString(2, telefone.getNumero());

			rsTel = ps.executeQuery();

			while (rsTel.next()) {

				String nome = rsTel.getString("NOME");
				String cargo = rsTel.getString("CARGO");
				String codArea = rsTel.getString("CODAREA");
				String Telefone = rsTel.getString("TELEFONE");
				int tipoTel = rsTel.getInt("TIPOTELEFONE");
				int compromissoId = rsTel.getInt("COMPROMISSOID");
				String email = rsTel.getString("EMAIL");

				switch (tipoTel) {

				case 2:

					contato = new Contato(nome, cargo, new Celular(codArea, Telefone), new Email(email),compromissoId);
					break;
				case 1:

					contato = new Contato(nome, cargo, new Fixo(codArea, Telefone), new Email(email),compromissoId);
					break;
				}

				contatobyTel.add(contato);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			UtilDAO.closeResultSet(rsTel);
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(contatobyTel);

		return contatobyTel;

	}

	@Override
	public boolean delete(Contato obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
