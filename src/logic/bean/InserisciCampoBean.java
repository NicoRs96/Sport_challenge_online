package logic.bean;


import logic.dao.InserisciCampoDao;
import logic.model.Campo;


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
