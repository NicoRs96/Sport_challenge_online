package dao;

import java.sql.*;
import java.util.Properties;

public class SportPreferitoDao {

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

    public boolean setSportPreferito(int utenteId, String sport) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        Statement stm = connection.createStatement();
        String q = "SELECT MAX(ID) AS ID FROM user_sport";
        ResultSet rs = stm.executeQuery(q);
        int id = 0;
        while (rs.next()) {
            if (rs.getString("ID") == null)
                id = 0;
            else id = rs.getInt("ID") + 1;
        }

        String query = String.format("INSERT INTO user_sport (ID, USER, SPORT, LIVELLO, PREFERITO) VALUES(%s, %s, '%s', 'DILETTANTE', 1) ON DUPLICATE KEY UPDATE PREFERITO=1",
                id, utenteId, sport);

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
