package model.entitiesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.entities.Compromisso;

public class CompromissoDAO {

	Connection conn;
	DateTimeFormatter formatDia = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatHora = DateTimeFormatter.ofPattern("HH:mm:ss");

	public CompromissoDAO(Connection conn) {

		this.conn = conn;

	}

	
	public boolean insert(Compromisso obj) {

		PreparedStatement ps = null;
		// PreparedStatement psTel = null;

		Compromisso cp = findByDateTime(obj.getData().format(formatDia).toString(), obj.getHorario().format(formatHora).toString());

		if (cp == null) {

			try {

				ps = conn.prepareStatement(
						"INSERT INTO COMPROMISSO(TITULO,DIA,ENDERECO,REFERENCIA,DESCRICAO,HORARIO) VALUES (?,?,?,?,?,?)");

				ps.setString(1, obj.getTitulo());
				ps.setDate(2, Date.valueOf(obj.getData()));
				ps.setString(3, obj.getEndereco());
				ps.setString(4, obj.getReferencia());
				ps.setString(5, obj.getDescricao());
				ps.setTime(6, Time.valueOf(obj.getHorario()));

				int status = ps.executeUpdate();

				System.out.println(status);

			} catch (SQLException e) {

				System.out.println(e.getMessage());

			} 

			return true;

		} else {

			return false;
		}

	}

	public Compromisso findByDateTime(String dia, String hora) {

		PreparedStatement ps = null;
		ResultSet rsComp = null;
		Compromisso compromisso=null;

		String query = "SELECT * FROM COMPROMISSO WHERE  DIA = ? AND HORARIO = ? ";

		try {

			ps = conn.prepareStatement(query);

			ps.setDate(1, Date.valueOf(LocalDate.parse(dia, formatDia)));
			ps.setTime(2, Time.valueOf(LocalTime.parse(hora)));

			rsComp = ps.executeQuery();

			while (rsComp.next()) {

				String titulo = rsComp.getString("TITULO");
				Date data = rsComp.getDate("DIA");
				String endereco = rsComp.getString("ENDERECO");
				String referencia = rsComp.getString("REFERENCIA");
				String descricao = rsComp.getString("DESCRICAO");
				Time horario = rsComp.getTime("HORARIO");
				

				compromisso = new Compromisso(titulo, data.toString(), horario.toString(), endereco,
						referencia, descricao);				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} 

		return compromisso;

	}
	
	


	public boolean update(Compromisso obj) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(
					"UPDATE COMPROMISSO SET TITULO = ? ,ENDERECO = ? ,REFERENCIA = ? ,DESCRICAO = ? WHERE DIA = ? AND HORARIO = ? ");

			ps.setString(1, obj.getTitulo());
			ps.setString(2, obj.getEndereco());
			ps.setString(3, obj.getReferencia());
			ps.setString(4, obj.getDescricao());
			ps.setDate(5, Date.valueOf(obj.getData()));
			ps.setTime(6, Time.valueOf(obj.getHorario()));

			ps.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage()+"oi");

		} 

		return true;
	}

	public void updateDiaHora(Compromisso novo, Compromisso antigo) {
		
		delete(findByDateTime(antigo.getData().toString(),antigo.getHorario().format(formatHora).toString()));
		insert(novo);		
		
	}
	

	public List<Compromisso> findAll() {
		
		
		Statement st = null;
		ResultSet rs =null;
		Compromisso compromisso = null;
		List<Compromisso> compromissos = new ArrayList<>();

		try {

			st = conn.createStatement();
			
			rs = st.executeQuery("SELECT * FROM COMPROMISSO");
			
			while(rs.next()) {
			
				String titulo = rs.getString("TITULO");
				Date data = rs.getDate("DIA");
				String endereco = rs.getString("ENDERECO");
				String referencia = rs.getString("REFERENCIA");
				String descricao = rs.getString("DESCRICAO");
				Time horario = rs.getTime("HORARIO");
				

				compromisso = new Compromisso(titulo,data.toString(), horario.toString(), endereco,
						referencia, descricao);
				

				compromissos.add(compromisso);				
				
				
			}
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 

		return compromissos;
	}

	
	public boolean delete(Compromisso obj) {
		
		
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM COMPROMISSO WHERE DIA = ? AND HORARIO = ? ");

			ps.setDate(1, Date.valueOf(obj.getData()));
			ps.setTime(2, Time.valueOf(obj.getHorario()));

			ps.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 

		return true;
	}

}
