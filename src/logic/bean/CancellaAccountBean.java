package bean;

import dao.CancellaAccountDao;

import java.sql.SQLException;

public class CancellaAccountBean {
    private CancellaAccountDao cancellaAccountDao = new CancellaAccountDao();

    public boolean deleteAccount(int id) throws SQLException {
        return cancellaAccountDao.deleteAccount(id);
    }
}
