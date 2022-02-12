package logic.bean;


import logic.dao.CreaTorneoDao;
import logic.model.Campo;
import logic.model.Torneo;


import java.sql.SQLException;
import java.util.List;

public class CreaTorneoBean {
    private final CreaTorneoDao creaTorneoDao = new CreaTorneoDao();

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
