package dao;

import java.sql.*;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

public class CercaCampoDao {

    public CercaCampoDao() {
        //nothing
    }

    public Connection getConnection() throws SQLException {


        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "admin");

        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportchallengeonline",
                connectionProps);
        return conn;

    }

    public Boolean isCityAvailable(String city) throws SQLException {
        String query = String.format("SELECT * FROM COMUNE WHERE NOME='%s'", city);
        Connection connection = getConnection();        
        Statement statement = null;
        ResultSet result = null;
        boolean check = false;
        	try {
        		statement = connection.createStatement();
			} catch (SQLException throwables) {
	            throwables.printStackTrace();
				
				
			}
        	finally {
        		
        		try {
        			result = statement.executeQuery(query);
				} 
        		catch (SQLException throwables) {
		            throwables.printStackTrace();
				}
        		finally {
	        		statement.close();
	        		if (result !=null && result.next()){
	        			check=true;
		        		result.close();
		        		}

	        		}
        		
				}
        
        connection.close();
        return check;

    }

    public SortedMap<String, TreeMap<String, String>> getCampo(String city, String sport, String data) throws SQLException {
        TreeMap<String, TreeMap<String, String>> campoInfo = new TreeMap<>();

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM CAMPO WHERE COMUNE='%s' AND SPORT='%s' AND DATA >= '%s' AND TORNEO = 0", city, sport, data);
        ResultSet resultSet = statement.executeQuery(query);
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
        connection.close();
        return campoInfo;
    }

    public boolean confermaPrenotazione(int utente, int campo) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        String query = "SELECT MAX(ID) FROM PRENOTAZIONE_CAMPO";
        ResultSet result = statement.executeQuery(query);
        int max = 0;
        if (result.next()) {
            max = (result.getString(1) == null) ? 0 : Integer.parseInt(result.getString(1))+1;

        }
        query = String.format("INSERT INTO PRENOTAZIONE_CAMPO(ID,User,Campo) VALUES(%s,'%s','%s')", max, utente, campo);
        statement.execute(query);
        query = String.format("UPDATE CAMPO SET AFFITTABILE = 0 WHERE ID = '%s'", campo);
        statement = connection.prepareStatement(query);
        if (statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;

    }

}
