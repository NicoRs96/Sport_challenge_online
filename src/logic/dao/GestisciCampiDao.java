package dao;

//import com.sun.source.tree.Tree;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

public class GestisciCampiDao {

    public GestisciCampiDao(){}

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

    public TreeMap<Integer, ArrayList<TreeMap<String, String>>> getCampi(int renterId) throws SQLException {
        TreeMap<Integer, ArrayList<TreeMap<String, String>>> campoInfo = new TreeMap<>();
        ArrayList<TreeMap<String, String>> infoList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM CAMPO WHERE renter= %s AND TORNEO = 0", renterId);
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            String id = ""+ resultSet.getInt("ID");
            String name = resultSet.getString("NOME");
            String comune = resultSet.getString("COMUNE");
            String indirizzo = resultSet.getString("INDIRIZZO");
            String desc = resultSet.getString("DESCRIZIONE");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String metodo = resultSet.getString("METODODIPAGAMENTO");
            String prezzo = resultSet.getString("PREZZO");
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
            info.put("PREZZO",prezzo);
            info.put("METODODIPAGAMENTO",metodo);
            info.put("SPORT",sport);
            info.put("AFFITTABILE", affittabile);
            infoList.add(info);

        }
        campoInfo.put(renterId,infoList);
        connection.close();
        return campoInfo;
    }

    public boolean setCampoAffittabile(int id) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("UPDATE CAMPO SET AFFITTABILE = 1 WHERE id = '%s'", id);
        Statement statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

    public boolean setCampoNonAffittabile(int id) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("UPDATE CAMPO SET AFFITTABILE = 0 WHERE id = '%s'", id);
        Statement statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

    public TreeMap<Integer, TreeMap<String, String>> getPrenotazioni(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("select p.id, c.NOME as 'CAMPO', c.`DATA`, c.ora as 'ORA', u.NOME as 'CLIENTE', u.COGNOME , c.PREZZO, u.TELEFONO from sportchallengeonline.prenotazione_campo p , sportchallengeonline.campo c , sportchallengeonline.`user` u where p.campo = c.ID and p.`user` = u.ID and c.renter = '%s'", id);
        TreeMap<Integer, TreeMap<String, String>> prenotazioneInfo = new TreeMap<>();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            int idPrenotaz = resultSet.getInt("ID");
            String campo = resultSet.getString("CAMPO");
            String date = resultSet.getString("DATA");
            String ora = resultSet.getString("ORA");
            String prezzo = resultSet.getString("PREZZO");
            String nomeCliente = resultSet.getString("CLIENTE");
            String cognome = resultSet.getString("COGNOME");
            String telefono = resultSet.getString("TELEFONO");
            TreeMap<String, String> info = new TreeMap<>();

            info.put("CAMPO", campo);
            info.put("DATA", date);
            info.put("ORA", ora);
            info.put("NOMECLIENTE", nomeCliente);
            info.put("PREZZO",prezzo);
            info.put("COGNOMECLIENTE",cognome);
            info.put("TELEFONO",telefono);
            prenotazioneInfo.put(idPrenotaz,info);
        }

        connection.close();
        return prenotazioneInfo;
    }

    public boolean cancellaPrenotazione(int id) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("DELETE FROM PRENOTAZIONE_CAMPO WHERE id = '%s'", id);
        Statement statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }


}
