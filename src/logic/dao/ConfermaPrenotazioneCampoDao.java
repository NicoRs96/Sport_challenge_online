package dao;

import model.Persona;

import java.sql.*;

public class ConfermaPrenotazioneCampoDao {

	public ConfermaPrenotazioneCampoDao() {
		//nothing
	}
	
   

    public Persona getRenterById(String renter) throws SQLException {
    	
    	Connection connection;
    	Statement statement =null;
    	ResultSet resultSet =null;
    	try {
        connection = DBConnectionSingleton.getConnectionInstance();
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM user WHERE id = '%s'", renter);
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nome = resultSet.getString("NOME");
            String cognome = resultSet.getString("COGNOME");
            String datadinascita = resultSet.getString("DATADINASCITA");
            String telefono = "" + resultSet.getString("TELEFONO");
            String email = "" + resultSet.getString("EMAIL");
            String isRenter = "" + resultSet.getString("RENT");
            Persona persona = new Persona(id, nome, cognome, email, Date.valueOf(datadinascita).toLocalDate(), telefono, isRenter);
            connection.close();
            return persona;
        }
    	}
    	catch (Exception e) {
			// nothing
		}
    	finally {
    		 try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
		return null;
        
    }

    public boolean confermaPrenotazione(int utente, int campo) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	Statement statement2 = null;
    	ResultSet resultSet =null;
    	boolean risultato = false;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();

        String query = "SELECT MAX(ID) FROM PRENOTAZIONE_CAMPO";
        resultSet = statement.executeQuery(query);
        int max = 0;
        if (resultSet.next()) {
            max = (resultSet.getString(1) == null) ? 0 : Integer.parseInt(resultSet.getString(1))+1;

        }
        query = String.format("INSERT INTO PRENOTAZIONE_CAMPO(ID,User,Campo) VALUES(%s,'%s','%s')", max, utente, campo);
        statement.execute(query);
        query = String.format("UPDATE CAMPO SET AFFITTABILE = 0 WHERE ID = '%s'", campo);
        statement2 = connection.prepareStatement(query);
        if (statement2.executeUpdate(query) == 0) {
            risultato= false;
        }
        else {
        risultato=true;}

    	}
    	
    	catch (Exception e) {
			// nothing
		}
    	finally {
    		 try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
     	     try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
     	     try { if (statement2!=null) statement2.close(); } catch (Exception e) { /* Ignored */ }

		}
    return risultato;
    }
}
