package bean;

import dao.GestisciTorneiRenterDao;
import model.Persona;
import model.Torneo;

import java.sql.SQLException;
import java.util.List;

public class GestisciTorneiRenterBean {
    private GestisciTorneiRenterDao gestisciTorneiRenterDao = new GestisciTorneiRenterDao();

    public GestisciTorneiRenterBean() {
    	//constructor
    }

    public List<Torneo> getTorneiByRenterId(int id) throws SQLException {
        return gestisciTorneiRenterDao.getTorneiByRenterId(id);
    }

    public List<Persona> getIscrittiByTorneoId(int id) throws SQLException {
        return gestisciTorneiRenterDao.getIscrittiByTorneoId(id);
    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException{
        return gestisciTorneiRenterDao.confermaIscrizione(utenteId, torneoId);
    }

    public boolean cancellaIscrizione(int utenteId, int torneoId) throws SQLException{
        return gestisciTorneiRenterDao.cancellaIscrizione(utenteId, torneoId);
    }
}
