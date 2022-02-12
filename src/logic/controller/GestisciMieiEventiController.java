package logic.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.bean.GestisciMieiEventiBean;
import logic.model.Campo;
import logic.model.Persona;
import logic.model.Torneo;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

public class GestisciMieiEventiController implements Initializable {
    private Persona persona;
    private final GestisciMieiEventiBean gestisciMieiEventiBean = new GestisciMieiEventiBean();

    @FXML
    private Button esciBTN;
    @FXML
    private TableColumn<Campo, String> nomeCol;
    @FXML
    private TableColumn<Campo, Integer> idCol;
    @FXML
    private TableColumn<Campo, String> comuneColGMEC;
    @FXML
    private TableColumn<Campo, String> indirizzoColGMEC;
    @FXML
    private TableColumn<Campo, String> descColGMEC;
    @FXML
    private TableColumn<Campo, String> dataColGMEC;
    @FXML
    private TableColumn<Campo, String> oraColGMEC;
    @FXML
    private TableColumn<Campo, String> sportCol;
    @FXML
    private TableColumn<Campo, String> prezzoCol;

    @FXML
    private TableColumn<Torneo, Integer> idCol1;
    @FXML
    private TableColumn<Torneo, String> nomeCol1;
    @FXML
    private TableColumn<Torneo, String> cittaCol1;
    @FXML
    private TableColumn<Torneo, String> indirizzoCol1;
    @FXML
    private TableColumn<Torneo, String> dataCol1;
    @FXML
    private TableColumn<Torneo, String> oraCol1;
    @FXML
    private TableColumn<Torneo, String> sportCol1;
    @FXML
    private TableColumn<Torneo, String> confermatoCol;
    @FXML
    private TableColumn<Torneo, Double> prezzoCol1;
    @FXML
    private TableColumn<Torneo, String> descCol1;
    @FXML
    private TableView<Campo> campiTVGMEC;

    @FXML
    private TableView<Torneo> torneiTV;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCampi();
        setTornei();
    }

    public void getCampi() throws SQLException, ParseException {

        ObservableList<Campo> values = FXCollections.
                observableArrayList();

        int renterId = persona.getId();
        SortedMap<Integer, ArrayList<TreeMap<String, String>>> campos = gestisciMieiEventiBean.getCampi(renterId);
        for (TreeMap<String, String> info : campos.get(renterId)) {
            int id = Integer.parseInt(info.get("ID"));
            String nomeGMEC = info.get("NOME");
            String comuneGMEC = info.get("COMUNE");
            String indirizzoGMEC = info.get("INDIRIZZO");
            String descGMEC = info.get("DESC");
            String dateGMEC = info.get("DATA");
            String oraGMEC = info.get("ORA");
            String prezzoGMEC = info.get("PREZZO");
            String modPpagamentoGMEC = info.get("METODODIPAGAMENTO");
            String sportGMEC = info.get("SPORT");
            int isAffittabile = Integer.parseInt(info.get("AFFITTABILE"));

            Campo campoGMEC = new Campo(id, nomeGMEC, comuneGMEC, indirizzoGMEC,""+renterId,isAffittabile);
            campoGMEC.setDesc(descGMEC);
            campoGMEC.setData(dateGMEC);
            campoGMEC.setOra(oraGMEC);
            campoGMEC.setSport(sportGMEC);
            campoGMEC.setPrezzo(prezzoGMEC);
            campoGMEC.setModPagamento(modPpagamentoGMEC);

            values.add(campoGMEC);
            campiTVGMEC.setItems(values);

           
        }

    }

    public void getTornei() throws SQLException {
        Torneo torneo = gestisciMieiEventiBean.getTorneoByUtenteId(persona.getId());
        if(torneo == null) return;

        torneiTV.getItems().add(torneo);


    }

    public void setCampi(){
        idCol.setCellValueFactory(new PropertyValueFactory<Campo,Integer>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("nome"));
        comuneColGMEC.setCellValueFactory(new PropertyValueFactory<Campo,String>("comune"));
        indirizzoColGMEC.setCellValueFactory(new PropertyValueFactory<Campo,String>("indirizzo"));
        descColGMEC.setCellValueFactory(new PropertyValueFactory<Campo,String>("desc"));
        dataColGMEC.setCellValueFactory(new PropertyValueFactory<Campo,String>("data"));
        oraColGMEC.setCellValueFactory(new PropertyValueFactory<Campo,String>("ora"));
        sportCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("sport"));
        prezzoCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("prezzo"));
    }

    public void setTornei(){
        idCol1.setCellValueFactory(new PropertyValueFactory<Torneo,Integer>("id"));
        nomeCol1.setCellValueFactory(new PropertyValueFactory<Torneo,String>("nome"));
        dataCol1.setCellValueFactory(new PropertyValueFactory<Torneo,String>("data"));
        oraCol1.setCellValueFactory(new PropertyValueFactory<Torneo,String>("ora"));
        prezzoCol1.setCellValueFactory(new PropertyValueFactory<Torneo,Double>("prezzo"));
        cittaCol1.setCellValueFactory(new PropertyValueFactory<Torneo,String>("citta"));
        indirizzoCol1.setCellValueFactory(new PropertyValueFactory<Torneo,String>("indirizzo"));
        descCol1.setCellValueFactory(new PropertyValueFactory<Torneo,String>("desc"));
        sportCol1.setCellValueFactory(new PropertyValueFactory<Torneo,String>("sport"));
        confermatoCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("confermato"));
    }

    public void indietro() throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/HomepageSportman.fxml"));
        Parent root = loader.load();
        HomePageSportmanController homePageSportmanController = loader.getController();
        homePageSportmanController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void cancellaPrenotazione() throws SQLException, ParseException {
        if(campiTVGMEC.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE SELEZIONE CAMPO");
            alert.setContentText("Non � stato selezionato nessun campo, per favore riprova.");
            alert.showAndWait();
            return;
        }

        Campo campo = (Campo)  campiTVGMEC.getSelectionModel().getSelectedItem();
        if(gestisciMieiEventiBean.cancellaPrenotazioneCampo(campo.getId())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("PRENOTAZIONE CANCELLATA");
            alert.setContentText("Prenotazione cancellata con successo.");
            alert.showAndWait();

            getCampi();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setContentText("Errore nel sistema, contatta un amministratore.");
        alert.showAndWait();

    }

    public void cancellaTorneo() throws SQLException {
        if(torneiTV.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE SELEZIONE TORNEO");
            alert.setContentText("Non � stato selezionato nessun torneo, per favore riprova.");
            alert.showAndWait();
            return;
        }

        Torneo torneo = (Torneo)  torneiTV.getSelectionModel().getSelectedItem();
        if(gestisciMieiEventiBean.cancellaPrenotazioneTorneo(torneo.getId(), persona.getId())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ISCRIZIONE CANCELLATA");
            alert.setContentText("Iscrizione cancellata con successo.");
            alert.showAndWait();

            getTornei();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setContentText("Errore nel sistema, contatta un amministratore.");
        alert.showAndWait();

    }
}
