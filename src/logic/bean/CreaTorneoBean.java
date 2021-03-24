package bean;

import dao.CreaTorneoDao;
import model.Campo;

import java.sql.SQLException;
import java.util.List;

public class CreaTorneoBean {
    private CreaTorneoDao creaTorneoDao = new CreaTorneoDao();

    public CreaTorneoBean() {}

    public List<Campo> getCampiByRenterId(int id) throws SQLException {
        return creaTorneoDao.getCampyByRenterId(id);
    }

    public boolean inserisciTorneo(String nome, int campo, String data, String ora,  int etaMin, int numMinP, String datascadenza, double prezzo,String metodo, String desc) throws SQLException {
        return creaTorneoDao.inserisciTorneo(nome, campo, data, ora, etaMin, numMinP, datascadenza, prezzo, metodo, desc);
    }

}
