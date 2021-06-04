package dao;

import model.Campo;

import java.sql.*;
import java.util.SortedMap;
import java.util.TreeMap;


public class CercaTorneoDao {
	
	String comuneStringCtd ="COMUNE";
	String renterStringCtd ="RENTER";
	String indirizzoStringCtd = "INDIRIZZO";
	String prezzoStringCtd = "PREZZO";
	String sportStringCtd = "SPORT";

    

    public SortedMap<Integer, TreeMap<String, String>> getTornei(String city,String data) throws SQLException {
        TreeMap<Integer, TreeMap<String, String>> torneoInfo = new TreeMap<>();

    Connection connection;
	Statement statement = null;
	ResultSet resultSet = null;
	try {
    	 connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("SELECT * FROM TORNEO t,CAMPO c WHERE c.COMUNE='%s' AND t.DATA >= '%s' AND c.TORNEO = 1 AND t.campo = c.id", city, data);
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String nome = resultSet.getString("NOME");
            int id = resultSet.getInt("ID");
            String comune = resultSet.getString(comuneStringCtd);
            String campo = resultSet.getString("c.ID");
            String indirizzo = resultSet.getString(indirizzoStringCtd);
            String desc = resultSet.getString("DESCRIZIONE");
            String renter = "" + resultSet.getInt(renterStringCtd);
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String metodo = resultSet.getString("MODALITAPAGAMENTO");
            String prezzo = resultSet.getString(prezzoStringCtd);
            String eta = resultSet.getString("ETA");
            String numMin = resultSet.getString("NUMEROMIN");
            String dataS = resultSet.getString("DATASCADENZA");
            String sport = resultSet.getString(sportStringCtd);
            TreeMap<String, String> info = new TreeMap<>();
            info.put("NOME", nome);
            info.put("CAMPO", campo);
            info.put("COMUNE", comune);
            info.put("INDIRIZZO", indirizzo);
            info.put("DESC", desc);
            info.put("RENTER", renter);
            info.put("DATA", date);
            info.put("ORA", ora);
            info.put("PREZZO", prezzo);
            info.put("METODODIPAGAMENTO", metodo);
            info.put("ETA", eta);
            info.put("NUMEROMIN", numMin);
            info.put("DATASCADENZA", dataS);
            info.put("SPORT", sport);
            torneoInfo.put(id, info);

        }
       }
       catch (Exception e) {
		// nothing
	}
       finally {
    	   DBConnectionSingleton.closeRS(resultSet);
      		DBConnectionSingleton.closeSTMT(statement);
	}
      
        return torneoInfo;
    }

    public int getNumIscritti(int id) throws SQLException {
        int count = 0;
        
        Connection connection;
		Statement statement = null;
		ResultSet resultSet = null;
        try{
        	connection = DBConnectionSingleton.getConnectionInstance();
        
        statement = connection.createStatement();
        String query = String.format("SELECT count(*) AS NUM FROM prenotazione_torneo WHERE TORNEO = %s", id);
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            count = resultSet.getInt("NUM");
        }
        }
        
        catch (Exception e) {
        	//nothing
        	}
        finally {
        	DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        return count;
    }


    public Campo getCampoById(int id) throws SQLException {
        boolean variabile=false;
        Campo campo = null;

    	Connection connection;
    	Statement statement = null;
    	ResultSet resultSet = null;
    	
    	try {
    	
    	 connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("SELECT * FROM campo WHERE ID = %s", id);
         resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String name = resultSet.getString("NOME");
            String comune = resultSet.getString(comuneStringCtd);
            String indirizzo = resultSet.getString(indirizzoStringCtd);
            String renter = "" + resultSet.getInt(renterStringCtd);
            String isAffittabile = "" + resultSet.getString("AFFITTABILE");
            String sport = "" + resultSet.getString(sportStringCtd);
            campo = new Campo(id, name, comune, indirizzo, renter, Integer.parseInt(isAffittabile));
            campo.setSport(sport);
            variabile=true;
            connection.close();
        }
        }
    	catch (Exception e) {
			// nothing
		}
    	finally {
    		DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        if (variabile) {
        	return campo;
        }else {
        	return null;}
    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException {
    	
    	Connection connection;
    	Statement statement = null;

    	try {
    		connection = DBConnectionSingleton.getConnectionInstance();
            statement = connection.createStatement();
            String query = String.format("INSERT INTO PRENOTAZIONE_TORNEO(User,Torneo) VALUES('%s','%s')", utenteId, torneoId);
            statement.execute(query);
		} 
    	catch (Exception e) {
			// niente
		}
    	finally {
    		
       		DBConnectionSingleton.closeSTMT(statement);		}
        return true;

    }
}
