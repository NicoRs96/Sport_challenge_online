package bean;

import dao.ScegliLivelloDao;

import java.sql.SQLException;

public class ScegliLivelloBean {
    private ScegliLivelloDao scegliLivelloDao = new ScegliLivelloDao();

    public boolean setLivello(int utenteId, String sport, String livello) throws SQLException{
        return scegliLivelloDao.setLivello(utenteId, sport, livello);
    }
}
