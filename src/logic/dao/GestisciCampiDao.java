package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class GestisciCampiDao {
	
	

    public GestisciCampiDao(){
    	//constructor
    }
    
    static final String PREZZO_STRING = "PREZZO";
    
    

    public SortedMap<Integer, ArrayList<TreeMap<String, String>>> getCampi(int renterId) throws SQLException {
        TreeMap<Integer, ArrayList<TreeMap<String, String>>> campoInfo = new TreeMap<>();
        ArrayList<TreeMap<String, String>> infoList = new ArrayList<>();
        
        Connection connection;
        Statement statement=null;
        ResultSet resultSet=null;
        
        try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("SELECT * FROM CAMPO WHERE renter= %s AND TORNEO = 0", renterId);
         resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            String id = ""+ resultSet.getInt("ID");
            String name = resultSet.getString("NOME");
            String comune = resultSet.getString("COMUNE");
            String indirizzo = resultSet.getString("INDIRIZZO");
            String desc = resultSet.getString("DESCRIZIONE");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String metodo = resultSet.getString("METODODIPAGAMENTO");
            String prezzo = resultSet.getString(PREZZO_STRING);
            String sport = resultSet.getString("SPORT");
            String affittabile = resultSet.getString("AFFITTABILE");
            TreeMap<String, String> info = new TreeMap<>();
            info.put("ID", id);
            info.put("COMUNE", comune);
            info.put("INDIRIZZO", indirizzo);
            info.put("DESC", desc);
            info.put("DATA", date);
            info.put("ORA", ora);
            info.put("NOME", name);
            info.put(PREZZO_STRING,prezzo);
            info.put("METODODIPAGAMENTO",metodo);
            info.put("SPORT",sport);
            info.put("AFFITTABILE", affittabile);
            infoList.add(info);

        }
        campoInfo.put(renterId,infoList);
        }
        catch (Exception e) {
			// nothing
		}
        finally {
        	DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        
        return campoInfo;
    }

  

    public boolean setCampoNonAffittabile(int id) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	boolean risultato=true;
    	
    	try {
    	connection = DBConnectionSingleton.getConnectionInstance();
        String query = String.format("UPDATE CAMPO SET AFFITTABILE = 0 WHERE id = '%s'", id);
         statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            risultato= false;
        }
    	}
    	catch (Exception e) {
    		//nothing
    	}
    	finally {
       		 if (statement!=null) try {statement.close();} catch (Exception e2) {/*ignored*/}		}
        return risultato;
    }

    public SortedMap<Integer, TreeMap<String, String>> getPrenotazioni(int id) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	ResultSet resultSet = null;
        TreeMap<Integer, TreeMap<String, String>> prenotazioneInfo = new TreeMap<>();

    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
        String query = String.format("select p.id, c.NOME as 'CAMPO', c.`DATA`, c.ora as 'ORA', u.NOME as 'CLIENTE', u.COGNOME , c.PREZZO, u.TELEFONO from sportchallengeonline.prenotazione_campo p , sportchallengeonline.campo c , sportchallengeonline.`user` u where p.campo = c.ID and p.`user` = u.ID and c.renter = '%s'", id);
         resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            int idPrenotaz = resultSet.getInt("ID");
            String campo = resultSet.getString("CAMPO");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String prezzo = resultSet.getString(PREZZO_STRING);
            String nomeCliente = resultSet.getString("CLIENTE");
            String cognome = resultSet.getString("COGNOME");
            String telefono = resultSet.getString("TELEFONO");
            TreeMap<String, String> info = new TreeMap<>();

            info.put("CAMPO", campo);
            info.put("DATA", date);
            info.put("ORA", ora);
            info.put("NOMECLIENTE", nomeCliente);
            info.put(PREZZO_STRING,prezzo);
            info.put("COGNOMECLIENTE",cognome);
            info.put("TELEFONO",telefono);
            prenotazioneInfo.put(idPrenotaz,info);
        }}
    	catch (Exception e) {
			// nothing
		}
    	finally {
    		DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}

        return prenotazioneInfo;
    }

    public boolean aggiornaOrCancellaPrenotazione(String query) throws SQLException {
    	Connection connection;
    	Statement statement=null;
    	boolean risultato=true;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
        
         statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            risultato= false;
        }}
    	catch (Exception e) {
			// nothing
		}
    	finally {
       		DBConnectionSingleton.closeSTMT(statement);		}
        return risultato;
    }


}
