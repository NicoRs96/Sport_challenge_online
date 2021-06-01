package controller;

import java.io.IOException;
import java.time.LocalDate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Campo;
import model.Meteo;
import model.Torneo;

public class MeteoController {
	
	Torneo torneo;
	Campo campo;
	String cittaString;
	
	

	public MeteoController(Torneo torneo) {
		this.torneo=torneo;
		this.cittaString=torneo.getCitta();
		}
	public MeteoController(Campo campo) {
		this.campo=campo;
		this.cittaString=campo.getComune();
	}
	
	public Meteo getMeteoTorneo() {

	    Meteo meteo = new Meteo(cittaString);

	    Document doc = null;
	    try {
	        doc = Jsoup.connect("https://www.ilmeteo.it/meteo/" + torneo.getCitta()).get();
	        Elements newsHeadlines = doc.getElementsByTag("li");
		    for (Element headline : newsHeadlines) {
		        if (headline.text().split(" ").length > 2) {
		            updateMeteo(headline, meteo);
		        }
		    }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return meteo;
	}
	
	private boolean controllaData() {
		
		boolean expression=false;
		if(torneo.getData().getMonth() == LocalDate.now().getMonth() && torneo.getData().getDayOfMonth() <= LocalDate.now().getDayOfMonth()+15)
			expression=true;
			
		return expression;
	}
	private boolean controllaTemp(String tmin, String tmax) {
		
		boolean expression=false;
		
		if(tmax.isEmpty() || tmin.isEmpty())
			expression=true;
		return expression;
			
		
	}
	
	private void updateMeteo(Element headline, Meteo meteo) {
	    String tmax = headline.getElementsByClass("tmax").text();
	    String tmin = headline.getElementsByClass("tmin").text();
	    String giorno = headline.getElementsByTag("span").first() != null ? headline.getElementsByTag("span").first().text() : "";
	    boolean rain  = headline.getElementsByClass("s flag_pioggia").isEmpty();
	    boolean nuvoloso  = headline.getElementsByClass("s ss3").isEmpty();
	    boolean sole  = headline.getElementsByClass("s ss1").isEmpty();
	    if(controllaTemp(tmin, tmax))
	        return;

	    if((controllaData() && (giorno.split(" ")[1].equals(""+torneo.getData().getDayOfMonth())))){
	        if(!rain)
	            meteo.setT("1");
	        else if(!nuvoloso)
	            meteo.setT("2");
	        else if(!sole)
	            meteo.setT("3");
	        meteo.settMin(tmin);
	        meteo.settMax(tmax);          
	    }
	}
	
	}
	
	

