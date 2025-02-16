package logic.bean;

import logic.dao.CercaCampoDao;
import logic.dao.ConfermaPrenotazioneCampoDao;
import logic.exception.ConnectionClosedFXException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;

public class CercaCampoBean {
	
	public CercaCampoBean() {
		//nothing
	}
	
	private final CercaCampoDao cercaCampoDao = new CercaCampoDao();
	private final ConfermaPrenotazioneCampoDao confermaPrenotazioneCampoDao = new ConfermaPrenotazioneCampoDao();
	
	String citta;
	String sport;
	LocalDate data;
	
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	public int checkData() {
		if(data.getYear() < LocalDateTime.now().getYear() ||
                (data.getYear() == LocalDateTime.now().getYear() && data.getMonth().getValue() < LocalDateTime.now().getMonth().getValue()) ||
                (data.getYear() == LocalDateTime.now().getYear()
                        && data.getMonth() == LocalDateTime.now().getMonth()
                        && data.getDayOfMonth() < LocalDateTime.now().getDayOfMonth())) {
			return 1;
		}
		return 0;
	}

	public boolean isCityAvailable(String citta) throws SQLException {
		
		boolean b=false;
		
		if(Boolean.TRUE.equals(cercaCampoDao.isCityAvailable(citta))) {
			b=true;
			}
		return b;
	
	}

	public SortedMap<String, TreeMap<String, String>> getCampo(String city, String sport, String data) throws SQLException, ConnectionClosedFXException, ClassNotFoundException {
		return cercaCampoDao.getCampo(city, sport, data);
	}

	public boolean confermaPrenotazione(int utente, int campo) throws SQLException {
		return confermaPrenotazioneCampoDao.confermaPrenotazione(utente, campo);
	}
}
