package dao;

import model.Campo;
import model.Torneo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreaTorneoDao {

    

    public List<Campo> getCampyByRenterId(int id) throws SQLException {
    	
    	
        List<Campo> campi = new ArrayList<>();
        Connection connection;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
        	connection = DBConnectionSingleton.getConnectionInstance();
        	statement = connection.createStatement();
        	String query = String.format("SELECT * FROM CAMPO WHERE renter= %s AND TORNEO = 1", id);
        	resultSet = statement.executeQuery(query);
        	while(resultSet.next()) {
        		Campo campo = new Campo(resultSet.getString("NOME"), resultSet.getString("COMUNE"),
                    resultSet.getString("INDIRIZZO"));
        		campo.setData(resultSet.getString("DATA"));
        		campo.setOra(resultSet.getString("ORA"));
        		campo.setId(resultSet.getInt("ID"));
        		campi.add(campo);
        	}
        }
        catch (Exception e) {
			// nothing
		}
        finally {
        	DBConnectionSingleton.closeRS(resultSet);
       		DBConnectionSingleton.closeSTMT(statement);
		}
        return campi;
    }

    public boolean inserisciTorneo(Torneo torneo) throws SQLException {
    	
    	Connection connection;
    	Statement statement=null;
    	boolean check=false;
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
         String query = String.format("INSERT INTO torneo(NOME,CAMPO,DATA,ORA,ETA,NUMEROMIN,DATASCADENZA,PREZZO,MODALITAPAGAMENTO,DESCRIZIONE) VALUES('%s',%s, '%s','%s',%s,%s,'%s',%s,'%s','%s')", torneo.getNome(), torneo.getCampo(), torneo.getData(),torneo.getOra(),torneo.getEtaMin(),torneo.getNumMinPart(),torneo.getDataScadenza(),torneo.getPrezzo(),torneo.getMetodoPagamento(), torneo.getDesc());
         statement.execute(query);
         check = true;
        } catch (Exception e) {
			// nothing
		}
        finally {
       		DBConnectionSingleton.closeSTMT(statement);		}
        return check;}

    
}
