package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class CancellaAccountDao {


    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "admin");

        try {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportchallengeonline",
                connectionProps);}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        	
		}
        return conn;

    }

    public boolean deleteAccount(int id) throws SQLException {
        Statement stm = null;
        try {
        Connection connection = getConnection();
        
        stm = connection.createStatement();}
        catch (SQLException throwables) {
            throwables.printStackTrace();
		}
               
        String query = String.format("DELETE FROM USER WHERE ID = %s", id);
        
        try {
            if(stm!=null) {
            	stm.execute(query);}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        finally {
					
         if (stm!=null)
        	{stm.close();}
        	 }
        return false;
    }

}
