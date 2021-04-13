package bean;

import dao.CreaTorneoDao;
import model.Campo;
import model.Torneo;

import java.sql.SQLException;
import java.util.List;

public class CreaTorneoBean {
    private CreaTorneoDao creaTorneoDao = new CreaTorneoDao();

    public CreaTorneoBean() {
    	//constructor
    }

    public List<Campo> getCampiByRenterId(int id) throws SQLException {
        return creaTorneoDao.getCampyByRenterId(id);
    }

    public boolean inserisciTorneo(Torneo torneo) throws SQLException {
        return creaTorneoDao.inserisciTorneo(torneo);
    }

}
