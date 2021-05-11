package bean;

import dao.PartecipantiTorneoDao;
import model.Campo;
import model.Persona;
import model.Torneo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PartecipantiTorneoBean {
    private final PartecipantiTorneoDao partecipantiTorneoDao = new PartecipantiTorneoDao();

    public List<Persona> getIscrittiByTorneoId(Torneo torneo) throws SQLException {
        return partecipantiTorneoDao.getIscrittiByTorneoId(torneo);
    }

    public Persona getPersonaByEmail(String email) throws SQLException {
        return partecipantiTorneoDao.getPersonaByEmail(email);
    }

    public boolean sendInvite(Persona p, Torneo t, Persona invitato) throws SQLException {
        return partecipantiTorneoDao.sendInvite(p, t, invitato);
    }

    public boolean confermaIscrizione(int utenteId, int torneoId) throws SQLException {
        return partecipantiTorneoDao.confermaIscrizione(utenteId, torneoId);
    }

    public Campo getCampoById(int campoId) throws SQLException {
        return partecipantiTorneoDao.getCampoById(campoId);
    }
}
