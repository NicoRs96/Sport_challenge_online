package logic.bean;


import logic.dao.GestisciCampiDao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class GestisciCampiBean {
    private final GestisciCampiDao gestisciCampiDao = new GestisciCampiDao();

    public GestisciCampiBean(){
    	//constructor
    }
    public SortedMap<Integer, ArrayList<TreeMap<String, String>>> getCampi(int renterId) throws SQLException, ParseException {
        return gestisciCampiDao.getCampi(renterId);
    }

    public boolean setCampoAffittabile(int id) throws SQLException {
        return gestisciCampiDao.aggiornaOrCancellaPrenotazione(String.format("UPDATE CAMPO SET AFFITTABILE = 1 WHERE id = '%s'", id));

    }

    public boolean setCampoNonAffittabile(int id) throws SQLException {
        return gestisciCampiDao.setCampoNonAffittabile(id);
    }

    public SortedMap<Integer, TreeMap<String, String>> getPrenotazioni(int id) throws SQLException {
        return gestisciCampiDao.getPrenotazioni(id);
    }

    public boolean cancellaPrenotazione(int id) throws SQLException {
        return gestisciCampiDao.aggiornaOrCancellaPrenotazione(String.format("DELETE FROM PRENOTAZIONE_CAMPO WHERE id = '%s'",id));
    }
}
