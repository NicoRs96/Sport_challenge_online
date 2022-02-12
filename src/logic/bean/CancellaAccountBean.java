package logic.bean;

import logic.dao.CancellaAccountDao;

import java.sql.SQLException;

public class CancellaAccountBean {
    private final CancellaAccountDao cancellaAccountDao = new CancellaAccountDao();

    public boolean deleteAccount(int id) throws SQLException {
        return cancellaAccountDao.deleteAccount(id);
    }
}
