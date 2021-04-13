package bean;

import dao.InserisciCampoDao;
import model.Campo;
import model.Torneo;

import java.sql.SQLException;

public class InserisciCampoBean {
    private InserisciCampoDao inserisciCampoDao = new InserisciCampoDao();

    public InserisciCampoBean(){
    	//constructor
    }

    public boolean inserisciCampo(Campo campo, String torneo) throws SQLException {
        return inserisciCampoDao.inserisciCampo(campo, torneo);
    }


}
