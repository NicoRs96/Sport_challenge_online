package dao;

import model.Invito;
import model.Persona;
import model.Torneo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InviteNotificationDao {

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

    public List<Invito> getInvites(Persona persona) throws SQLException {
        List<Invito> inviti = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM user_invite where receiverId = %s and isRead = 0", persona.getId());
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int receiverId = resultSet.getInt("receiverId");
            int senderId = resultSet.getInt("senderId");
            int torneoId = resultSet.getInt("torneoId");
            byte isRead = resultSet.getByte("isRead");

            Persona sender = getPersonaById(senderId);
            Torneo t = getTorneoById(torneoId);

            if (sender == null || t == null)
                continue;

            inviti.add(new Invito(id, sender, persona, t, isRead));
        }

        return inviti;
    }

    public Persona getPersonaById(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM user WHERE id = %s", id);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String nome = resultSet.getString("NOME");
            String cognome = resultSet.getString("COGNOME");
            String datadinascita = resultSet.getString("DATADINASCITA");
            String telefono = "" + resultSet.getString("TELEFONO");
            String email = "" + resultSet.getString("EMAIL");
            String isRenter = "" + resultSet.getString("RENT");
            Persona persona = new Persona(id, nome, cognome, email, Date.valueOf(datadinascita).toLocalDate(), telefono, isRenter);
            connection.close();
            return persona;
        }
        return null;
    }

    public Torneo getTorneoById(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM torneo WHERE id = %s", id);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String nome = resultSet.getString("NOME");
            String campo = "" + resultSet.getInt("CAMPO");
            String data = resultSet.getString("DATA");
            String ora = "" + resultSet.getString("ORA");
            int etaMin = resultSet.getInt("ETA");
            double prezzo = resultSet.getDouble("PREZZO");
            int numMinPart = resultSet.getInt("NUMEROMIN");
            Torneo torneo = new Torneo(nome, campo, LocalDate.parse(data), ora, prezzo, etaMin, numMinPart);
            torneo.setId(id);
            connection.close();
            return torneo;
        }
        return null;
    }

    public void acceptInvite(Persona utente, Torneo torneo) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("INSERT INTO PRENOTAZIONE_TORNEO(USER,TORNEO,CONFERMATO) VALUES('%s','%s', 0)", utente.getId(), torneo.getId());
        statement.execute(query);
        query = String.format("UPDATE user_invite SET isRead = 1 WHERE torneoId = %s and receiverId = %s", torneo.getId(), utente.getId());
        statement = connection.prepareStatement(query);
        if (statement.executeUpdate(query) == 0) {
            connection.close();
        }
        connection.close();

    }

    public void removeInvite(int id) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("UPDATE user_invite SET isRead = 1 WHERE id = %s ", id);
        Statement statement = connection.prepareStatement(query);
        if (statement.executeUpdate(query) == 0) {
            connection.close();
        }
        connection.close();
    }
}
