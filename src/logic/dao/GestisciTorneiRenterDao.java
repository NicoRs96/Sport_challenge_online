package dao;

import model.Persona;
import model.Torneo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GestisciTorneiRenterDao {

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

    public List<Torneo> getTorneiByRenterId(int id) throws SQLException {
        List<Torneo> tornei = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM TORNEO t,CAMPO c WHERE c.RENTER = %s AND c.TORNEO = 1 AND t.CAMPO = c.ID", id);
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            Torneo torneo = new Torneo(resultSet.getInt("ID"),resultSet.getString("NOME"),resultSet.getString("c.NOME"),
                    resultSet.getString("DATA"),
                    resultSet.getString("ORA"),
                    resultSet.getDouble("PREZZO"),
                    resultSet.getInt("ETA"),
                    resultSet.getInt("NUMEROMIN"),
                    resultSet.getString("DATASCADENZA"),
                    resultSet.getString("MODALITAPAGAMENTO"),
                    resultSet.getString("DESCRIZIONE"));
            tornei.add(torneo);
        }
        return tornei;
    }

    public List<Persona> getIscrittiByTorneoId(int id) throws SQLException {
        List<Persona> iscritti = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM PRENOTAZIONE_TORNEO pt," +
                "USER u, CAMPO c, TORNEO t, USER_SPORT su " +
                "WHERE pt.TORNEO = %s AND pt.USER = U.ID AND pt.TORNEO = t.ID AND t.CAMPO = c.ID AND c.SPORT = su.SPORT AND su.USER = u.ID", id);
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            resultSet.beforeFirst();
            while(resultSet.next()) {
                Persona persona = new Persona(resultSet.getInt("pt.USER"),
                        resultSet.getString("u.NOME"),
                        resultSet.getString("u.COGNOME"),
                        resultSet.getString("u.EMAIL"),
                        LocalDate.parse(resultSet.getString("u.DATADINASCITA")),
                        resultSet.getString("u.TELEFONO"),
                        resultSet.getString("u.RENT")
                        );
                String livello = (resultSet.getString("su.LIVELLO") == null) ? "DILETTANTE" : resultSet.getString("su.LIVELLO");
                persona.setLivello(livello);
                iscritti.add(persona);
            }
        }
        else{
            query = String.format("SELECT * FROM PRENOTAZIONE_TORNEO pt," +
                    "USER u WHERE pt.TORNEO = %s AND pt.USER = U.ID" , id);
            resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    Persona persona = new Persona(resultSet.getInt("pt.USER"),
                            resultSet.getString("u.NOME"),
                            resultSet.getString("u.COGNOME"),
                            resultSet.getString("u.EMAIL"),
                            LocalDate.parse(resultSet.getString("u.DATADINASCITA")),
                            resultSet.getString("u.TELEFONO"),
                            resultSet.getString("u.RENT")
                    );
                    persona.setLivello("DILETTANTE");
                    iscritti.add(persona);
                }

        }


        connection.close();
        return iscritti;
    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException{
        Connection connection = getConnection();
        String query = String.format("UPDATE PRENOTAZIONE_TORNEO SET CONFERMATO =1 WHERE USER = %s AND TORNEO = %s ", utenteId, torneoId);
        Statement statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

    public boolean cancellaIscrizione(int utenteId, int torneoId) throws SQLException{
        Connection connection = getConnection();
        String query = String.format("UPDATE PRENOTAZIONE_TORNEO SET CONFERMATO = 0 WHERE USER = %s AND TORNEO = %s ", utenteId, torneoId);
        Statement statement = connection.prepareStatement(query);
        if(statement.executeUpdate(query) == 0) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }
}