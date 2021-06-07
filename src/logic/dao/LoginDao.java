package dao;

import java.sql.*;
import java.util.SortedMap;
import java.util.TreeMap;

import exception.ConnectionClosedFXException;


public class LoginDao {

    public LoginDao(){
    	//nothing
    }

    

    public SortedMap<String, String> authenticate(String email, String password) throws SQLException {

        TreeMap<String, String> user = new TreeMap<>();
        Connection connection;
        Statement stm=null;
        ResultSet resultSet=null;
        try {
         connection = DBConnectionSingleton.getConnectionInstance();
         stm = connection.createStatement();
        String query = String.format("SELECT * FROM USER WHERE email = '%s' AND password='%s'",email, password);
         resultSet = stm.executeQuery(query);
        while(resultSet.next()) {
            user.put("ID", ""+resultSet.getInt("ID"));
            user.put("NOME", resultSet.getString("NOME"));
            user.put("COGNOME", resultSet.getString("COGNOME"));
            user.put("DATADINASCITA", resultSet.getDate("DATADINASCITA").toString());
            user.put("EMAIL", resultSet.getString("EMAIL"));
            user.put("PASSWORD", resultSet.getString("PASSWORD"));
            user.put("TELEFONO", resultSet.getString("TELEFONO"));
            user.put("RENT", "" +resultSet.getInt("RENT"));
        }
        }
        catch (Exception e) {
			
        }
        finally {
        	try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
    	     try { if (stm!=null) stm.close(); } catch (Exception e) { /* Ignored */ }
		}
        return user;
    }


}
