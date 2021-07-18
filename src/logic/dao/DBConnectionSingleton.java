package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.ConnectionClosedFXException;

public class DBConnectionSingleton {
	
	private static final String USER = "root";
    private static final String PASS = "admin";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sportchallengeonline";
    private static Connection conn = null;
	
    private DBConnectionSingleton() {
	
	    throw new IllegalStateException("Utility class");
	  }
	public static void closeConnectionIstance() throws SQLException
	{
		if (DBConnectionSingleton.conn != null)
		{
			conn.close();
			conn=null;
		}
	}
	public static boolean connectionExist(){
		return (conn != null);
	}
	public static Connection getConnectionInstance() throws ConnectionClosedFXException, SQLException, ClassNotFoundException 
	{
		if (DBConnectionSingleton.conn == null || DBConnectionSingleton.conn.isClosed())
		{
			 try 
		     {
				 conn = DriverManager.getConnection(DB_URL, USER, PASS);
		     }
			 catch (SQLException e) 
		     {
				 
				 throw new ConnectionClosedFXException("Problema alla connessione col DB:"+e.getMessage());
				 
		     }
		
		}
		return conn;
	}
	
	public static void closeRS(ResultSet resultSet) {
    	try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }

	}
	public static void closeSTMT(Statement statement) {
	     try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }

	}
	

}
