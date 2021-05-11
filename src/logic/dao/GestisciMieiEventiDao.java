package dao;

import model.Torneo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.SortedMap;
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

    public SortedMap<Integer, ArrayList<TreeMap<String, String>>> getCampi(int utenteId) throws SQLException {
        TreeMap<Integer, ArrayList<TreeMap<String, String>>> campoInfo = new TreeMap<>();
        ArrayList<TreeMap<String, String>> infoList = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM PRENOTAZIONE_CAMPO p, CAMPO c WHERE c.ID = p.CAMPO AND p.USER = %s", utenteId);
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            String idGMEDao = ""+ resultSet.getInt("p.ID");
            String nameGMEDao = resultSet.getString("NOME");
            String comuneGMEDao = resultSet.getString("COMUNE");
            String indirizzoGMEDao = resultSet.getString("INDIRIZZO");
            String descGMEDao = resultSet.getString("DESCRIZIONE");
            String dateGMEDao = resultSet.getString("DATA");
            String oraGMEDao = resultSet.getString("ORA");
            String metodoGMEDao = resultSet.getString("METODODIPAGAMENTO");
            String prezzo = resultSet.getString("PREZZO");
            String sportGMEDao = resultSet.getString("SPORT");
            String affittabileGMEDao = resultSet.getString("AFFITTABILE");
            TreeMap<String, String> info = new TreeMap<>();
            info.put("ID", idGMEDao);
            info.put("COMUNE", comuneGMEDao);
            info.put("INDIRIZZO", indirizzoGMEDao);
            info.put("DESC", descGMEDao);
            info.put("DATA", dateGMEDao);
            info.put("ORA", oraGMEDao);
            info.put("NOME", nameGMEDao);
            info.put("PREZZO",prezzo);
            info.put("METODODIPAGAMENTO",metodoGMEDao);
            info.put("SPORT",sportGMEDao);
            info.put("AFFITTABILE", affittabileGMEDao);
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
            Torneo torneo = new Torneo(
                    resultSet.getString("t.NOME"),
                    resultSet.getString("c.NOME"),
                    LocalDate.parse(resultSet.getString("t.DATA")),
                    resultSet.getString("t.ORA"),
                    resultSet.getDouble("t.PREZZO"),
                    resultSet.getInt("t.ETA"),
                    resultSet.getInt("t.NUMEROMIN")                    

            );
            
            torneo.setId(resultSet.getInt("T.ID"));
            torneo.setDataScadenza(resultSet.getString("t.DATASCADENZA"));
            torneo.setMetodoPagamento(resultSet.getString("t.MODALITAPAGAMENTO"));
            torneo.setDesc(resultSet.getString("t.DESCRIZIONE"));
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
