package logic.bean;


import logic.dao.CercaTorneoDao;
import logic.model.Campo;


import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;

public class CercaTorneoBean {
    private final CercaTorneoDao cercaTorneoDao = new CercaTorneoDao();

    public SortedMap<Integer, TreeMap<String, String>> getTornei(String city,String data) throws SQLException {
        return cercaTorneoDao.getTornei(city, data);
    }

    public int getNumIscritti(int id) throws SQLException {
        return cercaTorneoDao.getNumIscritti(id);
    }

    public Campo getCampoById(int id) throws SQLException {
        return cercaTorneoDao.getCampoById(id);
    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException {
        return cercaTorneoDao.confermaIscrizione(utenteId, torneoId);
    }
}
