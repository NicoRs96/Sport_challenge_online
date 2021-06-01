package dao;

import model.Persona;
import model.Torneo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestisciTorneiRenterDao {

  

    public List<Torneo> getTorneiByRenterId(int id) throws SQLException {
        List<Torneo> tornei = new ArrayList<>();
        Connection connection;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
        	connection = DBConnectionSingleton.getConnectionInstance();
        	statement = connection.createStatement();
        String query = String.format("SELECT * FROM TORNEO t,CAMPO c WHERE c.RENTER = %s AND c.TORNEO = 1 AND t.CAMPO = c.ID", id);
        	resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            Torneo torneo = new Torneo(resultSet.getString("NOME"),resultSet.getString("c.NOME"),
                    LocalDate.parse(resultSet.getString("DATA")),
                    resultSet.getString("ORA"),
                    resultSet.getDouble("PREZZO"),
                    resultSet.getInt("ETA"),
                    resultSet.getInt("NUMEROMIN"));
                   
            torneo.setId(resultSet.getInt("ID"));
            torneo.setDataScadenza(resultSet.getString("DATASCADENZA"));
            torneo.setMetodoPagamento(resultSet.getString("MODALITAPAGAMENTO"));
            torneo.setDesc(resultSet.getString("DESCRIZIONE"));
            tornei.add(torneo);
        }
        }
        catch (Exception e) {
			// nothing
		}
        finally {
        	 try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
        return tornei;
    }

    public List<Persona> getIscrittiByTorneoId(int id) throws SQLException {
        List<Persona> iscritti = new ArrayList<>();
        Connection connection;
        Statement statement=null;
        ResultSet resultSet=null;
        ResultSet resultSet2=null;
        
        try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM PRENOTAZIONE_TORNEO pt," +
                "USER u, CAMPO c, TORNEO t, USER_SPORT su " +
                "WHERE pt.TORNEO = %s AND pt.USER = U.ID AND pt.TORNEO = t.ID AND t.CAMPO = c.ID AND c.SPORT = su.SPORT AND su.USER = u.ID", id);
         resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            resultSet.beforeFirst();
            while(resultSet.next()) {
                Persona persona = new Persona(resultSet.getInt("pt.USER"),
                        resultSet.getString("u.NOME"),
                        resultSet.getString("u.COGNOME"),
                        resultSet.getString("u.EMAIL"),
                        LocalDate.parse(resultSet.getString("u.DATADINASCITA")),
                        resultSet.getString("u.TELEFONO"),
                        resultSet.getString("u.RENT")
                        );
                String livello = (resultSet.getString("su.LIVELLO") == null) ? "DILETTANTE" : resultSet.getString("su.LIVELLO");
                persona.setLivello(livello);
                iscritti.add(persona);
            }
        }
        else{
            query = String.format("SELECT * FROM PRENOTAZIONE_TORNEO pt," +
                    "USER u WHERE pt.TORNEO = %s AND pt.USER = U.ID" , id);
            resultSet2 = statement.executeQuery(query);
                while(resultSet.next()) {
                    Persona persona = new Persona(resultSet.getInt("pt.USER"),
                            resultSet2.getString("u.NOME"),
                            resultSet2.getString("u.COGNOME"),
                            resultSet2.getString("u.EMAIL"),
                            LocalDate.parse(resultSet.getString("u.DATADINASCITA")),
                            resultSet2.getString("u.TELEFONO"),
                            resultSet2.getString("u.RENT")
                    );
                    persona.setLivello("DILETTANTE");
                    iscritti.add(persona);
                }

        }
        }
        catch (Exception e) {
			// nothing
		}
        finally {
        	closeDBthings(statement, resultSet, resultSet2);
		}


        return iscritti;
    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException{
    	
    	Connection connection;
    	Statement statement=null;
    	boolean risultato=true;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         String query = String.format("UPDATE PRENOTAZIONE_TORNEO SET CONFERMATO =1 WHERE USER = %s AND TORNEO = %s ", utenteId, torneoId);
         statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            risultato=false;
        }
    	}
    	catch (Exception e) {
			// nothing
    	}
    	finally {
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
        return risultato;
    	
    }
    	
        
    

    public boolean cancellaIscrizione(int utenteId, int torneoId) throws SQLException{
        
    	Connection connection;
    	Statement statement=null;
    	boolean risultato=true;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         String query = String.format("UPDATE PRENOTAZIONE_TORNEO SET CONFERMATO = 0 WHERE USER = %s AND TORNEO = %s ", utenteId, torneoId);
         statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            risultato=false;
        }
    	}
    	catch (Exception e) {
			// nothing
    	}
    	finally {
     	    try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
		}
        return risultato;
    	
    }
    private void closeDBthings(Statement statement, ResultSet resultSet, ResultSet resultSet2) {
    	try { if(resultSet!=null) resultSet.close(); } catch (Exception e) { /* Ignored */ }
    	try { if(resultSet2!=null) resultSet2.close(); } catch (Exception e) { /* Ignored */ }

	     try { if (statement!=null) statement.close(); } catch (Exception e) { /* Ignored */ }
	}
    	
    	
}