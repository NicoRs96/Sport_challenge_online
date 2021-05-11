package bean;

import dao.InserisciCampoDao;
import model.Campo;

import java.sql.SQLException;

public class InserisciCampoBean {
    private final InserisciCampoDao inserisciCampoDao = new InserisciCampoDao();

    public InserisciCampoBean(){
    	//constructor
    }

    public boolean inserisciCampo(Campo campo, String torneo) throws SQLException {
        return inserisciCampoDao.inserisciCampo(campo, torneo);
    }


}
