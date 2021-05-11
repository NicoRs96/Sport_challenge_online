package bean;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.IscrizioneDao;

public class IscrivitiBean {
	
	
	public IscrivitiBean() {
	//nothing
	}
	
	private final IscrizioneDao iscrizioneDao = new IscrizioneDao();


	String nome;
	String cognome;
	LocalDate data;
	String mail;
	String pw;
	String telefono;
	Boolean cb;
	
	public Boolean getCb() {
		return cb;
	}
	public void setCb(Boolean cb) {
		this.cb = cb;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate localDate) {
		this.data = localDate;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public int checkData() {
		
		if((LocalDateTime.now().getYear() - data.getYear()) < 14) {
			return 1;			
		}
		return 0;
	}
	
	public int checkMail() {
		if(!mail.isEmpty()) {
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(mail);
			if (!matcher.find()) {
				return 1;}
		}
		return 0;
	}
	
	public int checkUtente() throws SQLException {
		if(iscrizioneDao.checkIfUserAlreadyExists(nome.toUpperCase(), cognome.toUpperCase())) {
			return 1;
		}
		return 0;
		
	}
	
	public int aggiungiUtente() throws SQLException {
		if (iscrizioneDao.addUser(nome.toUpperCase(), cognome.toUpperCase(),
				mail, data.toString(), pw,telefono, cb.toString())) {
			return 1;
		}
		return 0;
	}
	
	
	
}
	
	
