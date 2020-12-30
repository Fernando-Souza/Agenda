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
		boolean flag = false;

		try {

			ps = conn.prepareStatement("INSERT INTO CONTATO(NOME,CARGO,CODAREA,TELEFONE,TELTIPO) VALUES (?,?,?,?,?)");

			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getCargo());
			ps.setString(3, contato.getTelefone().get(0).getCodArea());
			ps.setString(4, contato.getTelefone().get(0).getNumero());

			switch (contato.getTelefone().get(0).getTipo()) {

			case "Celular":
				ps.setString(5, "2");
				break;
			case "Fixo":
				ps.setString(5, "1");
				break;

			}

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
		// TODO Auto-generated method stub
		return false;
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
					+ "TT.TIPOTELEFONE FROM CONTATO AS c LEFT JOIN EMAIL AS e ON e.CONTATO_CODAREA  = c.CODAREA "
					+ "AND e.CONTATO_TELEFONE  = c.TELEFONE LEFT  JOIN TIPOTELEFONE AS TT ON TT.TIPO_ID = c.TELTIPO");

			while (rsAll.next()) {

				String nome = rsAll.getString("NOME");
				String cargo = rsAll.getString("CARGO");
				String codAreaP = rsAll.getString("CODAREA");
				String telefoneP = rsAll.getString("TELEFONE");
				String tipoTel = rsAll.getString("TIPOTELEFONE");
				String email = rsAll.getString("EMAIL");

				switch (tipoTel) {

				case "Celular":

					contato = new Contato(nome, cargo, new Celular(tipoTel, codAreaP, telefoneP), new Email(email));

					break;
				case "Fixo":

					contato = new Contato(nome, cargo, new Fixo(tipoTel, codAreaP, telefoneP), new Email(email));
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

		String query = "SELECT c.NOME ,c.CARGO,c.CODAREA ,c.TELEFONE ,e.EMAIL,TT.TIPOTELEFONE FROM CONTATO AS c LEFT JOIN EMAIL AS e ON e.CONTATO_CODAREA = c.CODAREA AND e.CONTATO_TELEFONE = c.TELEFONE\n"
				+ "LEFT JOIN TIPOTELEFONE AS TT ON TT.TIPO_ID = c.TELTIPO WHERE UPPER(c.NOME) = UPPER(?) ";

		try {

			ps = conn.prepareStatement(query);

			ps.setString(1, name);

			rsName = ps.executeQuery();

			while (rsName.next()) {

				String nome = rsName.getString("NOME");
				String cargo = rsName.getString("CARGO");
				String codArea = rsName.getString("CODAREA");
				String telefone = rsName.getString("TELEFONE");
				String tipoTel = rsName.getString("TIPOTELEFONE");
				String email = rsName.getString("EMAIL");

				switch (tipoTel) {

				case "Celular":

					contato = new Contato(nome, cargo, new Celular(tipoTel, codArea, telefone), new Email(email));
					break;
				case "Fixo":

					contato = new Contato(nome, cargo, new Fixo(tipoTel, codArea, telefone), new Email(email));
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
		ResultSet rsName = null;
		Contato contato = null;
		List<Contato> contatobyTel = new ArrayList<>();

		String query = "SELECT c.NOME ,c.CARGO,c.CODAREA ,c.TELEFONE ,e.EMAIL,TT.TIPOTELEFONE FROM CONTATO AS c LEFT JOIN EMAIL AS e"
				+ " ON e.CONTATO_CODAREA = c.CODAREA AND e.CONTATO_TELEFONE = c.TELEFONE LEFT JOIN TIPOTELEFONE AS TT ON TT.TIPO_ID = c.TELTIPO  WHERE c.CODAREA = ? AND  c.TELEFONE = ? ";

		try {

			ps = conn.prepareStatement(query);

			ps.setString(1, telefone.getCodArea());
			ps.setString(2, telefone.getNumero());

			rsName = ps.executeQuery();

			while (rsName.next()) {

				String nome = rsName.getString("NOME");
				String cargo = rsName.getString("CARGO");
				String codArea = rsName.getString("CODAREA");
				String Telefone = rsName.getString("TELEFONE");
				String tipoTel = rsName.getString("TIPOTELEFONE");
				String email = rsName.getString("EMAIL");

				switch (tipoTel) {

				case "Celular":

					contato = new Contato(nome, cargo, new Celular(tipoTel, codArea, Telefone), new Email(email));
					break;
				case "Fixo":

					contato = new Contato(nome, cargo, new Fixo(tipoTel, codArea, Telefone), new Email(email));
					break;
				}

				contatobyTel.add(contato);

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
		System.out.println(contatobyTel);

		return contatobyTel;

	}

	@Override
	public boolean delete(Contato obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
