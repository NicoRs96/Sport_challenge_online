package logic.bean;


import logic.dao.GestisciTorneiRenterDao;
import logic.model.Persona;
import logic.model.Torneo;


import java.sql.SQLException;
import java.util.List;

public class GestisciTorneiRenterBean {
    private final GestisciTorneiRenterDao gestisciTorneiRenterDao = new GestisciTorneiRenterDao();

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
        return gestisciTorneiRenterDao.confermaOCancellaIscrizione(utenteId, torneoId,1);
    }

    public boolean cancellaIscrizione(int utenteId, int torneoId) throws SQLException{
        return gestisciTorneiRenterDao.confermaOCancellaIscrizione(utenteId, torneoId,0);
    }
}
