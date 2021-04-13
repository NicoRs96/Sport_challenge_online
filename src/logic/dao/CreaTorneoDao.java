package dao;

import model.Campo;
import model.Torneo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CreaTorneoDao {

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

    public List<Campo> getCampyByRenterId(int id) throws SQLException {
        List<Campo> campi = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM CAMPO WHERE renter= %s AND TORNEO = 1", id);
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            Campo campo = new Campo(resultSet.getInt("ID"), resultSet.getString("NOME"), resultSet.getString("COMUNE"),
                    resultSet.getString("INDIRIZZO"),resultSet.getString("DATA"), resultSet.getString("ORA"));
            campi.add(campo);
        }
        return campi;
    }

    public boolean inserisciTorneo(Torneo torneo) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("INSERT INTO torneo(NOME,CAMPO,DATA,ORA,ETA,NUMEROMIN,DATASCADENZA,PREZZO,MODALITAPAGAMENTO,DESCRIZIONE) VALUES('%s',%s, '%s','%s',%s,%s,'%s',%s,'%s','%s')", torneo.getNome(), torneo.getCampo(), torneo.getData(),torneo.getOra(),torneo.getEtaMin(),torneo.getNumMinPart(),torneo.getDataScadenza(),torneo.getPrezzo(),torneo.getMetodoPagamento(), torneo.getDesc());
        try {
            statement.execute(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}