package logic.bean;


import logic.dao.ConfermaPrenotazioneCampoDao;
import logic.model.Persona;

import java.sql.SQLException;

public class ConfermaPrenotazioneCampoBean {
    private final ConfermaPrenotazioneCampoDao confermaPrenotazioneCampoDao = new ConfermaPrenotazioneCampoDao();

    public Persona getRenterById(String renter) throws SQLException {
        return confermaPrenotazioneCampoDao.getRenterById(renter);
    }

    public boolean confermaPrenotazione(int utente, int campo) throws SQLException {
        return confermaPrenotazioneCampoDao.confermaPrenotazione(utente, campo);
    }
}
