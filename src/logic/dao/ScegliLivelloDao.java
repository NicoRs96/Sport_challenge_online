package dao;

import java.sql.*;
import java.util.Properties;

public class ScegliLivelloDao {

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


    public boolean setLivello(int utenteId, String sport, String livello) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        Statement stm = connection.createStatement();
        String q = "SELECT MAX(ID) AS ID FROM campo";
        ResultSet rsSLD = stm.executeQuery(q);
        int idSLD = 0;
        while(rsSLD.next()){
            if (rsSLD.getString("ID")==null)
                idSLD = 0;
            else idSLD = rsSLD.getInt("ID")+1;
        }

        String query = String.format("INSERT INTO user_sport (ID, USER, SPORT, LIVELLO, PREFERITO) VALUES(%s, %s, '%s', '%s', 0) ON DUPLICATE KEY UPDATE LIVELLO='%s'",
                idSLD, utenteId, sport, livello, livello);

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        connection.close();
        return true;
    }
}
