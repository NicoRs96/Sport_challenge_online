package bean;

import dao.InserisciCampoDao;

import java.sql.SQLException;

public class InserisciCampoBean {
    private InserisciCampoDao inserisciCampoDao = new InserisciCampoDao();

    public InserisciCampoBean(){}

    public boolean inserisciCampo(String nome, String comune, String indirizzo,String sport, String descrizione, int renter, String data, String ora, String metodo, String prezzo, String torneo) throws SQLException {
        return inserisciCampoDao.inserisciCampo(nome, comune, indirizzo, sport, descrizione, renter, data, ora, metodo, prezzo, torneo);
    }


}
