package model;

public class Meteo {
	private String tMin;
	private String tMax;
	private String t;
	private String cittaString;
	
	public Meteo (String citta) {
		this.cittaString=citta;
	}

	

	public String gettMin() {
		return tMin;
	}



	public void settMin(String tMin) {
		this.tMin = tMin;
	}



	public String gettMax() {
		return tMax;
	}



	public void settMax(String tMax) {
		this.tMax = tMax;
	}



	public String getT() {
		return t;
	}



	public void setT(String t) {
		this.t = t;
	}



	public String getCittaString() {
		return cittaString;
	}

	public void setCittaString(String cittaString) {
		this.cittaString = cittaString;
	}
	

}
