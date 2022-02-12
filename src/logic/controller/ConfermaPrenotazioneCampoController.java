package logic.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.bean.ConfermaPrenotazioneCampoBean;
import logic.exception.MeteoNotFoundException;
import logic.model.Campo;
import logic.model.Persona;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ConfermaPrenotazioneCampoController implements Initializable {
    private Persona persona;
    private Campo campo;
    private final ConfermaPrenotazioneCampoBean confermaPrenotazioneCampoBean = new ConfermaPrenotazioneCampoBean();

    @FXML
    private Text nomeTXT;
    @FXML
    private Text cognomeTXT;
    @FXML
    private Text emailTXT;
    @FXML
    private Text telefonoTXT;
    @FXML
    private Text nomeCTXT;
    @FXML
    private Text cittaTXT;
    @FXML
    private Text indirizzoTXT;
    @FXML
    private Text dataTXT;
    @FXML
    private Text oraTXT;
    @FXML
    private Text prezzoTXT;
    @FXML
    private Text mdpTXT;
    @FXML
    private Text telefonoRTXT;
    @FXML
    private Text meteoTXT;
    @FXML
    private Text meteoNDTXT;
    @FXML
    private Text tMinTXT;
    @FXML
    private Text tMaxTXT;
    @FXML
    private ImageView soleIV;
    @FXML
    private ImageView nuvoleIV;
    @FXML
    private ImageView pioggiaIV;
    @FXML
    private Button annullaBTN;
    @FXML
    private Button confermaBTN;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //not necessary
    }

    public void fill() throws SQLException, IOException {
        nomeTXT.setText("Nome: " + persona.getNome());
        cognomeTXT.setText("Cognome: " + persona.getCognome());
        emailTXT.setText("Email: " + persona.getMail());
        telefonoTXT.setText("Telefono: " + persona.getTelefono());
        nomeCTXT.setText("Nome: " + campo.getNome());
        cittaTXT.setText("Citta: " + campo.getComune());
        indirizzoTXT.setText("Indirizzo: " + campo.getIndirizzo());
        dataTXT.setText("Data: " + campo.getData());
        oraTXT.setText("Ora: " + campo.getOra());
        prezzoTXT.setText("Prezzo: " + campo.getPrezzo());
        mdpTXT.setText("Metodo di pagamento: " + campo.getModPagamento());

        Persona renter = confermaPrenotazioneCampoBean.getRenterById(campo.getRenter());
        String telefonoRenter = "";
        if (renter != null)
            telefonoRenter = renter.getTelefono();
        telefonoRTXT.setText("Contatto renter: " + telefonoRenter +"\n\n\n");
        

        getMeteo(campo);

    }


    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public void getMeteo(Campo campo) throws IOException {
    	try {
    		Document doc = Jsoup.connect("https://www.ilmeteo.it/meteo/" + campo.getComune()).get();
            if (doc != null) {
                makeMeteo(doc);
                    }
		} catch (IOException e) {
			MeteoNotFoundException meteoNotFoundException = new MeteoNotFoundException();
	    	meteoNotFoundException.showMessage();
	    	meteoNDTXT.setVisible(true);
		}
        
        
    }

    public void indietro() throws IOException {
        Stage stage = (Stage) annullaBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/CercaCampoSportivo.fxml"));
        Parent root = loader.load();
        CercaCampoSportivoController cercaCampoSportivoController = loader.getController();
        cercaCampoSportivoController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void prenota() throws SQLException, IOException {
        if (confermaPrenotazioneCampoBean.confermaPrenotazione(persona.getId(), campo.getId())) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Errore inatteso, riprova pi� tardi");
            alert.showAndWait();
            indietro();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS");
        alert.setContentText("Complimenti, hai prenotato con successo il campo.");
        alert.showAndWait();
        indietro();
        
        
    }
    
    private void makeMeteo(Document doc) {
    	LocalDate campoData = LocalDate.parse(campo.getData());
        List<Element> newsHeadlines = doc.getElementsByTag("li").stream().filter(x -> x.text().split(" ").length > 2).collect(Collectors.toList());
        for (Element headline : newsHeadlines) {
            String tmax = headline.getElementsByClass("tmax").text();
            String tmin = headline.getElementsByClass("tmin").text();
            String giorno = headline.getElementsByTag("span").first() != null ? headline.getElementsByTag("span").first().text() : "";
            boolean rain = headline.getElementsByClass("s flag_pioggia").isEmpty()
                        && headline.getElementsByClass("s ss10").isEmpty()
                        && headline.getElementsByClass("s ss16").isEmpty();
            boolean sole = headline.getElementsByClass("s ss1").isEmpty();
            if (tmax.isEmpty() || tmin.isEmpty())
                continue;

            if (campoData.getMonth() == LocalDate.now().getMonth() && campoData.getDayOfMonth() <= LocalDate.now().getDayOfMonth() + 15 && giorno.split(" ")[1].equals("" + campoData.getDayOfMonth())) {
                if (!rain)
                    pioggiaIV.setVisible(true);
                else if (!sole)
                    soleIV.setVisible(true);
                else {
					meteoTXT.setText("METEO\ninfo disponibili");
				}
                tMinTXT.setVisible(true);
                tMaxTXT.setVisible(true);
                tMinTXT.setText("Temperatura minima: \n" + tmin);
                tMaxTXT.setText("Temperatura massima: \n" + tmax);
                meteoTXT.setVisible(true);
		
	}
        }}
}
