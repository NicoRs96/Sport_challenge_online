package logic.dao;

import logic.model.Campo;
import logic.model.Persona;
import logic.model.Torneo;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartecipantiTorneoDao {
    String comuneString = "COMUNE";
    String renterString = "RENTER";
    String indirizzoString = "INDIRIZZO";
    String prezzoString = "PREZZO";
    String sportString = "SPORT";

    
    public List<Persona> getIscrittiByTorneoId(Torneo torneo) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet resultSet=null;
    	
        List<Persona> iscritti = new ArrayList<>();
        try {
          connection = DBConnectionSingleton.getConnectionInstance();
          
         statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM PRENOTAZIONE_TORNEO pt," +
                "USER u WHERE pt.TORNEO = %s AND pt.USER = U.ID", torneo.getId());
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Persona persona = new Persona(resultSet.getInt("pt.USER"),
                    resultSet.getString("u.NOME"),
                    resultSet.getString("u.COGNOME"),
                    resultSet.getString("u.EMAIL"),
                    LocalDate.parse(resultSet.getString("u.DATADINASCITA")),
                    resultSet.getString("u.TELEFONO"),
                    resultSet.getString("u.RENT")
            );

            String livello = getLivelloByPersonaIdAndSport(persona.getId(), torneo.getSport());
            persona.setLivello(livello);
            iscritti.add(persona);
        }
        }
        catch (Exception e) {
			//nothing
		}
        
        finally {
        	DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        return iscritti;
    }

    private String getLivelloByPersonaIdAndSport(int id, String sport) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet resultSet=null;
    	
        String livello = "DILETTANTE";
        try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * from user_sport where user = %s and sport = '%s'", id, sport);
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            livello = resultSet.getString("LIVELLO");
            
        }
        }
        catch (Exception e) {
			// nothing
		}
        finally {
        	DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        return livello;
    }

    public Persona getPersonaByEmail(String email) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet resultSet=null;
    	Persona persona = null;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM user WHERE email = '%s'", email.toLowerCase());
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nome = resultSet.getString("NOME");
            String cognome = resultSet.getString("COGNOME");
            String datadinascita = resultSet.getString("DATADINASCITA");
            String telefono = "" + resultSet.getString("TELEFONO");
            String isRenter = "" + resultSet.getString("RENT");
            persona = new Persona(id, nome, cognome, email, Date.valueOf(datadinascita).toLocalDate(), telefono, isRenter);
            
        }
    	}
    	catch (Exception e) {
			//nothing
		}
    	finally {
    		DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        return persona;
    }

    public boolean sendInvite(Persona p, Torneo t, Persona invitato) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	boolean check=false;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("INSERT INTO user_invite(senderId, receiverId, torneoId, isRead) VALUES (%s,%s,%s,0)", p.getId(), invitato.getId(), t.getId());
        statement.execute(query);
         check=true;
        } 
    	catch (Exception e) {
            e.printStackTrace();
            return check;
        }
    	finally {
       		DBConnectionSingleton.closeSTMT(statement);		}
    	return check;
    }

    public Campo getCampoById(int id) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet resultSet=null;
        Campo campo =null;

    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("SELECT * FROM campo WHERE ID = %s", id);
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String name = resultSet.getString("NOME");
            String comune = resultSet.getString(comuneString);
            String indirizzo = resultSet.getString(indirizzoString);
            String renter = "" + resultSet.getInt(renterString);
            String isAffittabile = "" + resultSet.getString("AFFITTABILE");
            String sport = "" + resultSet.getString(sportString);
            campo = new Campo(id, name, comune, indirizzo, renter, Integer.parseInt(isAffittabile));
            campo.setSport(sport);
            
        }
    	}
    	catch (Exception e) {
			// nothing
		}
    	finally {
    		DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        return campo;    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("INSERT INTO PRENOTAZIONE_TORNEO(User,Torneo) VALUES('%s','%s')", utenteId, torneoId);
        statement.execute(query);
        connection.close();
    }
    	catch (Exception e) {
			// nothing
		}
    	finally {
       		DBConnectionSingleton.closeSTMT(statement);
		}
        return true;

    }
    
}