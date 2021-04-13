package dao;

import java.sql.*;
import java.util.Properties;

import model.Campo;

public class InserisciCampoDao {

    public InserisciCampoDao(){
    	//constructor
    }

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

    public boolean inserisciCampo(Campo campo, String torneo) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        Statement stm = connection.createStatement();
        String q = "SELECT MAX(ID) AS ID FROM campo";
        ResultSet rs = stm.executeQuery(q);
        int id = 0;
        while(rs.next()){
            if (rs.getString("ID")==null)
                id = 0;
            else id = rs.getInt("ID")+1;
        }


        String query = String.format("INSERT INTO campo(ID, NOME, COMUNE, INDIRIZZO, SPORT, DESCRIZIONE, RENTER, DATA, ORA, METODODIPAGAMENTO, PREZZO, AFFITTABILE, TORNEO) " +
                "VALUES('%s','%s','%s','%s','%s','%s',%s,'%s','%s','%s', '%s', 0,'%s')",id , campo.getNome().toUpperCase(), campo.getComune().toUpperCase(), campo.getIndirizzo().toUpperCase(),
                campo.getSport().toUpperCase(), campo.getDesc().toUpperCase(),  campo.getRenter(), campo.getData(), campo.getOra(), campo.getModPagamento().toUpperCase(), campo.getPrezzo(), torneo);

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
