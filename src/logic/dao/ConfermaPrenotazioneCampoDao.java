package dao;

import model.Persona;

import java.sql.*;
import java.util.Properties;

public class ConfermaPrenotazioneCampoDao {

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

    public Persona getRenterById(String renter) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = String.format("SELECT * FROM user WHERE id = '%s'", renter);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
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
