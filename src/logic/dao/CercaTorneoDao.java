package dao;

import model.Campo;

import java.sql.*;
import java.text.ParseException;
import java.util.Properties;
import java.util.TreeMap;

public class CercaTorneoDao {

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

    public TreeMap<Integer, TreeMap<String, String>> getTornei(String city,String data) throws SQLException {
        TreeMap<Integer, TreeMap<String, String>> torneoInfo = new TreeMap<>();

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM TORNEO t,CAMPO c WHERE c.COMUNE='%s' AND t.DATA >= '%s' AND c.TORNEO = 1 AND t.campo = c.id", city, data);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String nome = resultSet.getString("NOME");
            int id = resultSet.getInt("ID");
            String comune = resultSet.getString("COMUNE");
            String campo = resultSet.getString("c.ID");
            String indirizzo = resultSet.getString("INDIRIZZO");
            String desc = resultSet.getString("DESCRIZIONE");
            String renter = "" + resultSet.getInt("RENTER");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String metodo = resultSet.getString("MODALITAPAGAMENTO");
            String prezzo = resultSet.getString("PREZZO");
            String eta = resultSet.getString("ETA");
            String numMin = resultSet.getString("NUMEROMIN");
            String dataS = resultSet.getString("DATASCADENZA");
            String sport = resultSet.getString("SPORT");
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
        connection.close();
        return torneoInfo;
    }

    public int getNumIscritti(int id) throws SQLException {
        int count = 0;
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT count(*) AS NUM FROM prenotazione_torneo WHERE TORNEO = %s", id);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            count = resultSet.getInt("NUM");
        }
        connection.close();
        return count;
    }


    public Campo getCampoById(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM campo WHERE ID = %s", id);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String name = resultSet.getString("NOME");
            String _id = "" + resultSet.getInt("ID");
            String comune = resultSet.getString("COMUNE");
            String indirizzo = resultSet.getString("INDIRIZZO");
            String desc = resultSet.getString("DESCRIZIONE");
            String renter = "" + resultSet.getInt("RENTER");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String metodo = resultSet.getString("METODODIPAGAMENTO");
            String prezzo = resultSet.getString("PREZZO");
            String isAffittabile = "" + resultSet.getString("AFFITTABILE");
            String sport = "" + resultSet.getString("SPORT");
            Campo campo = new Campo(id, name, comune, indirizzo, renter, isAffittabile);
            campo.setSport(sport);
            connection.close();
            return campo;
        }
        return null;
    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("INSERT INTO PRENOTAZIONE_TORNEO(User,Torneo) VALUES('%s','%s')", utenteId, torneoId);
        statement.execute(query);
        connection.close();
        return true;

    }
}
