package bean;

import dao.SportPreferitoDao;

import java.sql.SQLException;

public class SportPreferitoBean {
    private final SportPreferitoDao sportPreferitoDao = new SportPreferitoDao();

    public boolean setSportPreferito(int utenteId, String sport) throws SQLException{
        return sportPreferitoDao.setSportPreferito(utenteId, sport);
    }
}
