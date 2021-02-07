package dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.TreeMap;

public class CercaCampoDao {

    public CercaCampoDao(){
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
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM COMUNE WHERE NOME='%s'", city);

        ResultSet result = statement.executeQuery(query);

        if(result.next()){
            connection.close();
            return true;
        }
        connection.close();
        return false;
        
    }

    public TreeMap<String, TreeMap<String, String>> getCampo(String city, String sport, String data) throws SQLException, ParseException {
        TreeMap<String, TreeMap<String, String>> campoInfo = new TreeMap<>();

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM CAMPO WHERE COMUNE='%s' AND SPORT='%s' AND DATA >= '%s'", city, sport, data);
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            String name = resultSet.getString("NOME");
            String comune = resultSet.getString("COMUNE");
            String indirizzo = resultSet.getString("INDIRIZZO");
            String desc = resultSet.getString("DESCRIZIONE");
            String renter = ""+resultSet.getInt("RENTER");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            TreeMap<String, String> info = new TreeMap<>();
            info.put("COMUNE", comune);
            info.put("INDIRIZZO", indirizzo);
            info.put("DESC", desc);
            info.put("RENTER", renter);
            info.put("DATA", date);
            info.put("ORA", ora);
            campoInfo.put(name,info);

        }

        return campoInfo;
    }


}
