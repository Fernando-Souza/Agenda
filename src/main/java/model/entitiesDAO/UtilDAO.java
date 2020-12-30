package model.entitiesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UtilDAO {

	private static Connection conn = null;

	public static Connection getConnection() {

		String url ="jdbc:derby:DB;create=false";
		
		try {
			if (conn == null) {
					
				conn = DriverManager.getConnection(url);

			}
		} catch (SQLException sqe) {

			System.out.println(sqe.getMessage());

		}

		return conn;

	}	
	
	public static void closeConnection() {
		
		if(conn !=null) {
			try {
			conn.close();
			}
			catch(SQLException sqle) {
				
				System.out.println(sqle.getMessage());
				
			}
		}
		
	}
	
	public static void closeStatment(Statement st) {
		
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
				
			}
			
		}
	}
		
		public static void closeResultSet(ResultSet rs) {
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					
					System.out.println(e.getMessage());
					
				}
			}
		}
			
		
		
	}
