package dao;

import model.Invito;
import model.Persona;
import model.Torneo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InviteNotificationDao {

    

    public List<Invito> getInvites(Persona persona) throws SQLException {
        List<Invito> inviti = new ArrayList<>();
        Connection connection;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("SELECT * FROM user_invite where receiverId = %s and isRead = 0", persona.getId());
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int senderId = resultSet.getInt("senderId");
            int torneoId = resultSet.getInt("torneoId");
            byte isRead = resultSet.getByte("isRead");

            Persona sender = getPersonaById(senderId);
            Torneo t = getTorneoById(torneoId);

            if (sender == null || t == null)
                continue;

            inviti.add(new Invito(id, sender, persona, t, isRead));
        }
        }
        catch (Exception e) {
			// nothing
		}
        finally {
        	try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
    	     try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}

        return inviti;
    }

    public Persona getPersonaById(int id) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet resultSet=null;
        Persona personaInd = null;

    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM user WHERE id = %s", id);
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String nomeInd = resultSet.getString("NOME");
            String cognomeInd = resultSet.getString("COGNOME");
            String datadinascitaInd = resultSet.getString("DATADINASCITA");
            String telefonoInd = "" + resultSet.getString("TELEFONO");
            String emailInd = "" + resultSet.getString("EMAIL");
            String isRenterInd = "" + resultSet.getString("RENT");
            personaInd = new Persona(id, nomeInd, cognomeInd, emailInd, Date.valueOf(datadinascitaInd).toLocalDate(), telefonoInd, isRenterInd);
            
        }
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
    		 try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
        return personaInd;
        }

    public Torneo getTorneoById(int id) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet resultSet=null;
        Torneo torneo=null;

    	try {
    		connection = DBConnectionSingleton.getConnectionInstance();
    		statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM torneo WHERE id = %s", id);
        	resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String nome = resultSet.getString("NOME");
            String campo = "" + resultSet.getInt("CAMPO");
            String data = resultSet.getString("DATA");
            String ora = "" + resultSet.getString("ORA");
            int etaMin = resultSet.getInt("ETA");
            double prezzo = resultSet.getDouble("PREZZO");
            int numMinPart = resultSet.getInt("NUMEROMIN");
            torneo = new Torneo(nome, campo, LocalDate.parse(data), ora, prezzo, etaMin, numMinPart);
            torneo.setId(id);
            
        }
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
    		try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
    	     try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
        return torneo;    
        }

    public void acceptInvite(Persona utente, Torneo torneo) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	Statement statement2=null;
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("INSERT INTO PRENOTAZIONE_TORNEO(USER,TORNEO,CONFERMATO) VALUES('%s','%s', 0)", utente.getId(), torneo.getId());
        statement.execute(query);
        query = String.format("UPDATE user_invite SET isRead = 1 WHERE torneoId = %s and receiverId = %s", torneo.getId(), utente.getId());
        statement2 = connection.prepareStatement(query);
        statement2.executeUpdate(query);
        
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
     	    try { if (statement2!=null) statement2.close(); } catch (Exception e) { /* Ignored */ }

		}

    }

    public void removeInvite(int id) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         String query = String.format("UPDATE user_invite SET isRead = 1 WHERE id = %s ", id);
         statement = connection.prepareStatement(query);
        statement.executeUpdate(query);
            
        
    	}
    	catch (Exception e) {
			// nothing
    	}
    	finally {
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
    }
    	
    	
    	
        
}
