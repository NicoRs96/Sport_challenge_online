package logic.bean;


import logic.dao.ScegliLivelloDao;

import java.sql.SQLException;

public class ScegliLivelloBean {
    private final ScegliLivelloDao scegliLivelloDao = new ScegliLivelloDao();

    public boolean setLivello(int utenteId, String sport, String livello) throws SQLException{
        return scegliLivelloDao.setLivello(utenteId, sport, livello);
    }
}
