package dao;

import java.sql.*;

public class IscrizioneDao {

    public IscrizioneDao() {
    	//nothing
    }

    

    public Boolean checkIfUserAlreadyExists(String name, String surname) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet result=null;
    	boolean rs=false;
    	
    	try {

         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("SELECT * FROM USER WHERE NOME='%s' AND COGNOME='%s'", name, surname);

         result = statement.executeQuery(query);

        if(result.next()){
            connection.close();
            rs= true;
            }
    	}
    	catch (Exception e) {
    		//nothing
		}
    	 try { if(result!=null) result.close(); } catch (Exception e) { /* Ignored */ }
 	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
        return rs;
    }

    public Boolean addUser(String name, String surname, String email, String date, String password,String telephone, String isRent) throws SQLException {

    	Connection connection;
    	Statement statement=null;
    	
    	try {
    	
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("INSERT INTO USER(NOME, COGNOME, DATADINASCITA,EMAIL,PASSWORD, TELEFONO, RENT) VALUES('%s','%s','%s','%s','%s','%s',%s)", name, surname, date, email.toLowerCase(), password,telephone, isRent);

        statement.execute(query);
    	}
    	catch (Exception e) {
    		//return false
    		
		}
    	finally {
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
        return true;

    }



}
