package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import exception.ConnectionClosedException;

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
	public static Connection getConnectionInstance() throws ConnectionClosedException 
	{
		if (DBConnectionSingleton.conn == null)
		{
			 try 
		     {
				 conn = DriverManager.getConnection(DB_URL, USER, PASS);
		     }
			 catch (SQLException e) 
		     {
				 throw new ConnectionClosedException("Problema alla connessione col DB:"+e.getMessage());
				 
		     }
		
		}
		return conn;
	}
	

}
