package bean;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.CercaCampoDao;

public class CercaCampoBean {
	
	public CercaCampoBean() {
		//nothing
	}
	
	private CercaCampoDao cercaCampoDao = new CercaCampoDao();
	
	String città;
	String sport;
	LocalDate data;
	
	public String getCittà() {
		return città;
	}
	public void setCittà(String città) {
		this.città = città;
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
	public boolean isCityAvailable() throws SQLException {
		
		if(cercaCampoDao.isCityAvailable(città)) {		
			return true;
			
	}
		return false;
	
}
	}
