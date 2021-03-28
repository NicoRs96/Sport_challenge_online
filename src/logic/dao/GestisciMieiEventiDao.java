package dao;

import model.Torneo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class GestisciMieiEventiDao {


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

    public TreeMap<Integer, ArrayList<TreeMap<String, String>>> getCampi(int utenteId) throws SQLException {
        TreeMap<Integer, ArrayList<TreeMap<String, String>>> campoInfo = new TreeMap<>();
        ArrayList<TreeMap<String, String>> infoList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM PRENOTAZIONE_CAMPO p, CAMPO c WHERE c.ID = p.CAMPO AND p.USER = %s", utenteId);
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            String id = ""+ resultSet.getInt("p.ID");
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
        campoInfo.put(utenteId,infoList);
        connection.close();
        return campoInfo;
    }


    public Torneo getTorneoByUtenteId(int id) throws SQLException {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM PRENOTAZIONE_TORNEO p,CAMPO c,TORNEO t WHERE p.USER = %s AND p.TORNEO = t.ID AND t.CAMPO = c.ID", id);
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            Torneo torneo = new Torneo(resultSet.getInt("T.ID"),
                    resultSet.getString("t.NOME"),
                    resultSet.getString("c.NOME"),
                    resultSet.getString("t.DATA"),
                    resultSet.getString("t.ORA"),
                    resultSet.getDouble("t.PREZZO"),
                    resultSet.getInt("t.ETA"),
                    resultSet.getInt("t.NUMEROMIN"),
                    resultSet.getString("t.DATASCADENZA"),
                    resultSet.getString("t.MODALITAPAGAMENTO"),
                    resultSet.getString("t.DESCRIZIONE")

            );

            torneo.setCitta(resultSet.getString("c.COMUNE"));
            torneo.setIndirizzo(resultSet.getString("c.INDIRIZZO"));
            torneo.setSport(resultSet.getString("c.SPORT"));
            torneo.setIsConfermato(resultSet.getInt("p.CONFERMATO"));
            torneo.setConfermato();

            connection.close();
            return torneo;
        }
        connection.close();
        return null;
    }


    public boolean cancellaPrenotazioneCampo(int id)  throws SQLException{
        Connection connection = getConnection();
        String query = String.format("DELETE FROM PRENOTAZIONE_CAMPO WHERE id = %s", id);
        Statement statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

    public boolean cancellaPrenotazioneTorneo(int torneoId, int utenteId )  throws SQLException{
        Connection connection = getConnection();
        String query = String.format("DELETE FROM PRENOTAZIONE_TORNEO WHERE TORNEO = %s AND USER = %s", torneoId, utenteId);
        Statement statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }
}
