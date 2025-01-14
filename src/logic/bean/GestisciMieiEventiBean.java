package logic.bean;


import logic.dao.GestisciMieiEventiDao;
import logic.model.Torneo;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class GestisciMieiEventiBean {
    private final GestisciMieiEventiDao gestisciMieiEventiDao = new GestisciMieiEventiDao();

    public SortedMap<Integer, ArrayList<TreeMap<String, String>>> getCampi(int utenteId) throws SQLException, ParseException {
        return gestisciMieiEventiDao.getCampi(utenteId);
    }

    public Torneo getTorneoByUtenteId(int id) throws SQLException{
        return gestisciMieiEventiDao.getTorneoByUtenteId(id);
    }

    public boolean cancellaPrenotazioneCampo(int id)  throws SQLException{
        return gestisciMieiEventiDao.cancellaPrenotazioneCampo(id);
    }

    public boolean cancellaPrenotazioneTorneo(int torneoId,int utenteId)  throws SQLException{
        return gestisciMieiEventiDao.cancellaPrenotazioneTorneo(torneoId, utenteId);
    }

    public List<Torneo> getTornei(int id) throws SQLException{
        return gestisciMieiEventiDao.getTornei(id);
    }
}
