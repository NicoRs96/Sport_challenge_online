package dao;

import java.sql.*;
import java.util.Properties;
import java.util.TreeMap;

public class CancellaAccountDao {


    public Connection getConnection() throws SQLException {

        java.sql.Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "admin");

        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sportchallengeonline",
                connectionProps);
        return conn;

    }

    public boolean deleteAccount(int id) throws SQLException {
        TreeMap<String, String> user = new TreeMap<>();
        Connection connection = getConnection();
        Statement stm = connection.createStatement();
        String query = String.format("DELETE FROM USER WHERE ID = %s", id);
        try {
            stm.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }

}
