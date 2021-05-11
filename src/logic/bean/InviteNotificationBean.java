package bean;

import dao.InviteNotificationDao;
import model.Invito;
import model.Persona;
import model.Torneo;

import java.sql.SQLException;
import java.util.List;

public class InviteNotificationBean {
    private final InviteNotificationDao inviteNotificationDao = new InviteNotificationDao();

    public List<Invito> getInvites(Persona persona) throws SQLException {
        return inviteNotificationDao.getInvites(persona);
    }

    public void acceptInvite(Persona persona,Torneo torneo) throws SQLException {
        inviteNotificationDao.acceptInvite(persona, torneo);
    }

    public void removeInvite(int id) throws SQLException {
        inviteNotificationDao.removeInvite(id);
    }
}
