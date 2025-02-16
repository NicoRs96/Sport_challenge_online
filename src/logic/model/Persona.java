package logic.model;

import java.time.LocalDate;

public class Persona {

	private int id;
	private String nome;
	private String cognome;
	private LocalDate data;
	private String mail;
	private String telefono;
	private String isRenter;
	private String livello;
	private String sportPreferito;
	private String isConfermato;

	public Persona(int id, String nome, String cognome, String mail,LocalDate data, String telefono, String isRenter) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.data = data;
		this.telefono = telefono;
		this.isRenter = isRenter;
		this.isConfermato = "NO";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public void setRenter(String renter) {
		isRenter = renter;
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

	public LocalDate getDataPersona() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIsRenter() {
		
		return isRenter;
	}

	public void setIsRenter(String isRenter) {
		this.isRenter = isRenter;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public String getSportPreferito() {
		return sportPreferito;
	}

	public void setSportPreferito(String sportPreferito) {
		this.sportPreferito = sportPreferito;
	}

	public void setIsConfermato(int isConfermato){
		this.isConfermato = isConfermato == 0 ?  "NO" :  "SI";
	}

	public String getIsConfermato(){
		return isConfermato;
	}
}
