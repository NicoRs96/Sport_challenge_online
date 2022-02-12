package logic.dao;


import logic.exception.ConnectionClosedFXException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedMap;
import java.util.TreeMap;

public class CercaCampoDao {

    public CercaCampoDao() {
        //nothing
    }

    

    public Boolean isCityAvailable(String city) throws SQLException {
        String query = String.format("SELECT * FROM COMUNE WHERE NOME='%s'", city);
        Connection connection;        
        Statement statement = null;
        ResultSet result = null;
        boolean check = false;
        
        try {
			connection = DBConnectionSingleton.getConnectionInstance();
			statement = connection.createStatement();
			result = statement.executeQuery(query);       	
			
			if (result.next()){
    			check=true;}
        	
		} catch (Exception e) {
			// nothing
		}
        finally {   	   
    		
        	DBConnectionSingleton.closeRS(result);
        	DBConnectionSingleton.closeSTMT(statement);
		}
        
        return check;
        
    }   
        
        
        
        
    public SortedMap<String, TreeMap<String, String>> getCampo(String city, String sport, String data) throws SQLException, ConnectionClosedFXException, ClassNotFoundException {
        TreeMap<String, TreeMap<String, String>> campoInfo = new TreeMap<>();
        
        String query = String.format("SELECT * FROM CAMPO WHERE COMUNE='%s' AND SPORT='%s' AND DATA = '%s' AND TORNEO = 0", city, sport, data);
        
        
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
          connection = DBConnectionSingleton.getConnectionInstance();
          statement = connection.createStatement();
          resultSet = statement.executeQuery(query);
         while (resultSet.next()) {
            String name = resultSet.getString("NOME");
            String id = "" + resultSet.getInt("ID");
            String comune = resultSet.getString("COMUNE");
            String indirizzo = resultSet.getString("INDIRIZZO");
            String desc = resultSet.getString("DESCRIZIONE");
            String renter = "" + resultSet.getInt("RENTER");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String metodo = resultSet.getString("METODODIPAGAMENTO");
            String prezzo = resultSet.getString("PREZZO");
            String isAffittabile = "" + resultSet.getString("AFFITTABILE");
            TreeMap<String, String> info = new TreeMap<>();
            info.put("ID", id);
            info.put("COMUNE", comune);
            info.put("INDIRIZZO", indirizzo);
            info.put("DESC", desc);
            info.put("RENTER", renter);
            info.put("DATA", date);
            info.put("ORA", ora);
            info.put("PREZZO", prezzo);
            info.put("METODODIPAGAMENTO", metodo);
            info.put("AFFITTABILE", isAffittabile);
            campoInfo.put(name, info);

        }
        
    }
        catch (ConnectionClosedFXException e) {
			 throw new ConnectionClosedFXException("Problema alla connessione col DB:"+e.getMessage());
        	
		}
        
       finally {   	   
		
    	   DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
       
        return campoInfo;
       }



}
