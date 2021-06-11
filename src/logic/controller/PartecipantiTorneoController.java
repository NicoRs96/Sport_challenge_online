package controller;

import bean.PartecipantiTorneoBean;
import exception.MeteoIncompleteException;
import exception.MeteoNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Campo;
import model.Meteo;
import model.Persona;
import model.Torneo;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PartecipantiTorneoController implements Initializable {
    private Persona persona;
    private Torneo torneo;
    private List<Persona> iscritti;
    private final PartecipantiTorneoBean partecipantiTorneoBean = new PartecipantiTorneoBean();
    private String errore = "ERRORE";

    @FXML
    private Button esciBTN;
    @FXML
    private Text torneoTXT;
    @FXML
    private Text dataTXT;
    @FXML
    private Text oraTXT;
    @FXML
    private Text partecipantiTXT;
    @FXML
    private TextField amicoTF;
    @FXML
    private Text meteoTXT;
    @FXML
    private Text tMinTXT;
    @FXML
    private Text tMaxTXT;

    @FXML
    private TableView<Persona> partecipantiTVptc;
    @FXML
    private TableColumn<Persona, Integer> idColptc;
    @FXML
    private TableColumn<Persona, String> nomeColptc;
    @FXML
    private TableColumn<Persona, String> cognomeColptc;
    @FXML
    private TableColumn<Persona, Integer> livelloColptc;

    @FXML
    private ImageView soleIV;
    @FXML
    private ImageView nuvoleIV;
    @FXML
    private ImageView pioggiaIV;

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public void setPartecipanti() {
        idColptc.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("id"));
        nomeColptc.setCellValueFactory(new PropertyValueFactory<Persona, String>("nome"));
        cognomeColptc.setCellValueFactory(new PropertyValueFactory<Persona, String>("cognome"));
        livelloColptc.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("livello"));
    }

    public void setInfo() throws SQLException, IOException, MeteoIncompleteException {
        torneoTXT.setText(torneo.getNome());
        dataTXT.setText(torneo.getData().toString());
        oraTXT.setText(torneo.getOra());
        getMeteo(torneo);

        getIscritti();
    }

    public void getIscritti() throws SQLException {
        partecipantiTVptc.getItems().clear();
        iscritti = partecipantiTorneoBean.getIscrittiByTorneoId(torneo);
        partecipantiTVptc.getItems().addAll(iscritti);
        partecipantiTXT.setText("PARTECIPANTI: " + iscritti.size());
    }

    public void indietro() throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CercaTorneo.fxml"));
        Parent root = loader.load();
        CercaTorneoController cercaTorneoController = loader.getController();
        cercaTorneoController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        soleIV.setVisible(false);
        nuvoleIV.setVisible(false);
        pioggiaIV.setVisible(false);
        tMinTXT.setVisible(false);
        tMaxTXT.setVisible(false);
        meteoTXT.setVisible(false);
        setPartecipanti();
    }

    public void invita() throws SQLException {
        if (amicoTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errore);
            alert.setContentText("Inserisci un'email.");
            alert.showAndWait();
            return;
        }
        String email = amicoTF.getText();

        Persona invitato = partecipantiTorneoBean.getPersonaByEmail(email);
        if (invitato == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errore);
            alert.setContentText("Nessun utente trovato con questa email.");
            alert.showAndWait();
            return;
        }

        if (iscritti.stream().filter(x -> x.getId() == invitato.getId()).findAny().isPresent()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errore);
            alert.setContentText("L'utente è gia iscritto a questo torneo.");
            alert.showAndWait();
            return;
        }

        boolean result = partecipantiTorneoBean.sendInvite(persona, torneo, invitato);


        if (result) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ATTENZIONE");
            alert.setContentText("L'utente è stato invitato con successo.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errore);
        alert.setContentText("Hai gia invitato questo utente al torneo.");
        alert.showAndWait();
    }

    public void iscriviti() throws SQLException {

        int numIscritti = iscritti.size();
        Campo campo = partecipantiTorneoBean.getCampoById(torneo.getCampoId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RIEPILOGO ISCRIZIONE");
        String information = String.format("Riepilogo:\n----------\nNome Torneo: %s\n----------\nNumero Iscritti: %s\n----------\nCitta: %s\nPrezzo: %s\nModalita di pagamento: %s\nData: %s\nOra: %s\nSport: %s",
                torneo.getNome(),
                numIscritti,
                campo.getComune(),
                torneo.getPrezzo(),
                torneo.getMetodoPagamento(),
                torneo.getData(),
                torneo.getOra(),
                campo.getSport());
        alert.setContentText(information + "\n\nPREMERE OK per CONFERMARE");
        alert.showAndWait();
        if ((alert.getResult() == ButtonType.OK) &&
                (partecipantiTorneoBean.confermaIscrizione(persona.getId(), torneo.getId()))) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Iscrizione avvenuta con successo");
            alert.showAndWait();
            getIscritti();
        }
    }

    public void getMeteo(Torneo torneo) throws MeteoIncompleteException, MeteoNotFoundException  {
        
    	MeteoController meteoController = new MeteoController(torneo);
    	
    	Meteo meteo = meteoController.getMeteoTorneo();
    	
    	if (meteo.gettMin()!=null || meteo.gettMax()!=null) {
    	
    	try {
                        if(meteo.getT().equals("1"))
                            pioggiaIV.setVisible(true);
                        else if(meteo.getT().equals("2"))
                            nuvoleIV.setVisible(true);
                        else if(meteo.getT().equals("3"))
                            soleIV.setVisible(true);
                        tMinTXT.setVisible(true);
                        tMaxTXT.setVisible(true);
                        tMinTXT.setText("Temperatura minima: " + meteo.gettMin());
                        tMaxTXT.setText("Temperatura massima: " + meteo.gettMax());
                        meteoTXT.setVisible(true);
                        meteoTXT.setText("METEO: " + torneo.getCitta());    }
    	catch (Exception e) {
    		
    		    		
    	MeteoIncompleteException meteoIncompleteException = new MeteoIncompleteException(meteo);
    	meteoIncompleteException.showMessage();
    		
    	}
        
    	finally {
    		tMinTXT.setVisible(true);
            tMaxTXT.setVisible(true);
            tMinTXT.setText("Temperatura minima: " + meteo.gettMin());
            tMaxTXT.setText("Temperatura massima: " + meteo.gettMax());
            meteoTXT.setVisible(true);
            meteoTXT.setText("METEO: " + torneo.getCitta()); 
		}
    	}
    
    	
            
        
      
    }
    
    

}
