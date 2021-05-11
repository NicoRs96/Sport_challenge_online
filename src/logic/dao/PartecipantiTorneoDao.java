package dao;

import model.Campo;
import model.Persona;
import model.Torneo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PartecipantiTorneoDao {
    String comuneString = "COMUNE";
    String renterString = "RENTER";
    String indirizzoString = "INDIRIZZO";
    String prezzoString = "PREZZO";
    String sportString = "SPORT";

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

    public List<Persona> getIscrittiByTorneoId(Torneo torneo) throws SQLException {
        List<Persona> iscritti = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM PRENOTAZIONE_TORNEO pt," +
                "USER u WHERE pt.TORNEO = %s AND pt.USER = U.ID", torneo.getId());
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Persona persona = new Persona(resultSet.getInt("pt.USER"),
                    resultSet.getString("u.NOME"),
                    resultSet.getString("u.COGNOME"),
                    resultSet.getString("u.EMAIL"),
                    LocalDate.parse(resultSet.getString("u.DATADINASCITA")),
                    resultSet.getString("u.TELEFONO"),
                    resultSet.getString("u.RENT")
            );

            String livello = getLivelloByPersonaIdAndSport(persona.getId(), torneo.getSport());
            persona.setLivello(livello);
            iscritti.add(persona);
        }

        connection.close();
        return iscritti;
    }

    private String getLivelloByPersonaIdAndSport(int id, String sport) throws SQLException {
        String livello = "DILETTANTE";
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * from user_sport where user = %s and sport = '%s'", id, sport);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            livello = resultSet.getString("LIVELLO");
            break;
        }
        connection.close();
        return livello;
    }

    public Persona getPersonaByEmail(String email) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM user WHERE email = '%s'", email.toLowerCase());
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nome = resultSet.getString("NOME");
            String cognome = resultSet.getString("COGNOME");
            String datadinascita = resultSet.getString("DATADINASCITA");
            String telefono = "" + resultSet.getString("TELEFONO");
            String isRenter = "" + resultSet.getString("RENT");
            Persona persona = new Persona(id, nome, cognome, email, Date.valueOf(datadinascita).toLocalDate(), telefono, isRenter);
            connection.close();
            return persona;
        }
        return null;

    }

    public boolean sendInvite(Persona p, Torneo t, Persona invitato) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("INSERT INTO user_invite(senderId, receiverId, torneoId, isRead) VALUES (%s,%s,%s,0)", p.getId(), invitato.getId(), t.getId(), 0);
        try {
            statement.execute(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public Campo getCampoById(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM campo WHERE ID = %s", id);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String name = resultSet.getString("NOME");
            String comune = resultSet.getString(comuneString);
            String indirizzo = resultSet.getString(indirizzoString);
            String renter = "" + resultSet.getInt(renterString);
            String isAffittabile = "" + resultSet.getString("AFFITTABILE");
            String sport = "" + resultSet.getString(sportString);
            Campo campo = new Campo(id, name, comune, indirizzo, renter, Integer.parseInt(isAffittabile));
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