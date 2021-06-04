package dao;

import java.sql.*;

import model.Campo;

public class InserisciCampoDao {

    public InserisciCampoDao(){
    	//constructor
    }

    

    public boolean inserisciCampo(Campo campo, String torneo) throws SQLException {
    	Connection connection;
    	Statement statement=null;
    	Statement stm=null;
    	ResultSet rs=null;
    	
    	try {
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
         stm=connection.createStatement();
        String q = "SELECT MAX(ID) AS ID FROM campo";
        rs = stm.executeQuery(q);
        int id = 0;
        while(rs.next()){
            if (rs.getString("ID")==null)
                id = 0;
            else id = rs.getInt("ID")+1;
        }


        String query = String.format("INSERT INTO campo(ID, NOME, COMUNE, INDIRIZZO, SPORT, DESCRIZIONE, RENTER, DATA, ORA, METODODIPAGAMENTO, PREZZO, AFFITTABILE, TORNEO) " +
                "VALUES('%s','%s','%s','%s','%s','%s',%s,'%s','%s','%s', '%s', 0,'%s')",id , campo.getNome().toUpperCase(), campo.getComune().toUpperCase(), campo.getIndirizzo().toUpperCase(),
                campo.getSport().toUpperCase(), campo.getDesc().toUpperCase(),  campo.getRenter(), campo.getData(), campo.getOra(), campo.getModPagamento().toUpperCase(), campo.getPrezzo(), torneo);

        statement.execute(query);
       }
    	catch (Exception e) {
		// nothing
    	}
    	finally {
    		DBConnectionSingleton.closeRS(rs);
       		DBConnectionSingleton.closeSTMT(statement);
       		DBConnectionSingleton.closeSTMT(stm);


		}

        return true;

    }
}
