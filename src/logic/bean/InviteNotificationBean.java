package logic.bean;


import logic.dao.InviteNotificationDao;
import logic.model.Invito;
import logic.model.Persona;
import logic.model.Torneo;


import java.sql.SQLException;
import java.util.List;

public class InviteNotificationBean {
    private final InviteNotificationDao inviteNotificationDao = new InviteNotificationDao();

    public List<Invito> getInvites(Persona persona) throws SQLException {
        return inviteNotificationDao.getInvites(persona);
    }

    public void acceptInvite(Persona persona, Torneo torneo) throws SQLException {
        inviteNotificationDao.acceptInvite(persona, torneo);
    }

    public void removeInvite(int id) throws SQLException {
        inviteNotificationDao.removeInvite(id);
    }
}
