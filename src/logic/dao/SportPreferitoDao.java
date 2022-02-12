package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SportPreferitoDao {

    

    public boolean setSportPreferito(int utenteId, String sport) throws SQLException {
    	Connection connection;
    	Statement statement=null;
    	Statement stm=null;
    	ResultSet rsSPD=null;
    	
    	try {
    	
         connection = DBConnectionSingleton.getConnectionInstance();
         statement = connection.createStatement();
         stm = connection.createStatement();
        String q = "SELECT MAX(ID) AS ID FROM user_sport";
         rsSPD = stm.executeQuery(q);
        int idSPD = 0;
        while (rsSPD.next()) {
            if (rsSPD.getString("ID") == null)
                idSPD = 0;
            else idSPD = rsSPD.getInt("ID") + 1;
        }

        String query = String.format("INSERT INTO user_sport (ID, USER, SPORT, LIVELLO, PREFERITO) VALUES(%s, %s, '%s', 'DILETTANTE', 1) ON DUPLICATE KEY UPDATE PREFERITO=1",
                idSPD, utenteId, sport);

       statement.execute(query);
       
    	}
    	catch (Exception e) {
			// return false
		}
    	finally {
    		DBConnectionSingleton.closeRS(rsSPD);
       		DBConnectionSingleton.closeSTMT(statement);
       		DBConnectionSingleton.closeSTMT(stm);
    		

		}
        return true;
    }
}
