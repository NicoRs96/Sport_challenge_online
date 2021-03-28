package controller;

import bean.GestisciMieiEventiBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import model.Campo;
import model.Persona;
import model.Torneo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class GestisciMieiEventiController implements Initializable {
    private Persona persona;
    private GestisciMieiEventiBean gestisciMieiEventiBean = new GestisciMieiEventiBean();

    @FXML
    private Button esciBTN;
    @FXML
    private TableColumn<Campo, String> nomeCol;
    @FXML
    private TableColumn<Campo, Integer> idCol;
    @FXML
    private TableColumn<Campo, String> comuneCol;
    @FXML
    private TableColumn<Campo, String> indirizzoCol;
    @FXML
    private TableColumn<Campo, String> descCol;
    @FXML
    private TableColumn<Campo, String> dataCol;
    @FXML
    private TableColumn<Campo, String> oraCol;
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
    private TableView campiTV;

    @FXML
    private TableView torneiTV;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCampi();
        setTornei();
    }

    public void getCampi() throws SQLException, ParseException {

        ObservableList<Campo> values = FXCollections.
                observableArrayList();

        int renterId = persona.getId();
        TreeMap<Integer, ArrayList<TreeMap<String, String>>> campos = gestisciMieiEventiBean.getCampi(renterId);
        for (TreeMap<String, String> info : campos.get(renterId)) {
            int id = Integer.parseInt(info.get("ID"));
            String nome = info.get("NOME");
            String comune = info.get("COMUNE");
            String indirizzo = info.get("INDIRIZZO");
            String desc = info.get("DESC");
            String date = info.get("DATA");
            String ora = info.get("ORA");
            String prezzo = info.get("PREZZO");
            String modPpagamento = info.get("METODODIPAGAMENTO");
            String sport = info.get("SPORT");
            int isAffittabile = Integer.parseInt(info.get("AFFITTABILE"));

            Campo campo = new Campo(id, nome, comune, indirizzo,""+renterId,isAffittabile);
            campo.setDesc(desc);
            campo.setData(date);
            campo.setOra(ora);
            campo.setSport(sport);
            campo.setPrezzo(prezzo);
            campo.setModPagamento(modPpagamento);

            values.add(campo);
            campiTV.setItems(values);

           
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
        comuneCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("comune"));
        indirizzoCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("indirizzo"));
        descCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("desc"));
        dataCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("data"));
        oraCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("ora"));
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

    public void indietro(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomepageSportman.fxml"));
        Parent root = (Parent) loader.load();
        HomePageSportmanController homePageSportmanController = loader.getController();
        homePageSportmanController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void cancellaPrenotazione() throws SQLException, ParseException {
        if(campiTV.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE SELEZIONE CAMPO");
            alert.setContentText("Non è stato selezionato nessun campo, per favore riprova.");
            alert.showAndWait();
            return;
        }

        Campo campo = (Campo)  campiTV.getSelectionModel().getSelectedItem();
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
            alert.setContentText("Non è stato selezionato nessun torneo, per favore riprova.");
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
