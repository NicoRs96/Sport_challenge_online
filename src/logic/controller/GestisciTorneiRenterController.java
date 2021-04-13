package controller;

import bean.GestisciTorneiRenterBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Persona;
import model.Torneo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GestisciTorneiRenterController implements Initializable {
    private Persona persona;
    private GestisciTorneiRenterBean gestisciTorneiRenterBean = new GestisciTorneiRenterBean() ;

    @FXML
    private Button esciBTN;

    @FXML
    private Button confermaBTN;

    @FXML
    private Button cancellaBTN;


    @FXML
    private Button refreshBTN;


    @FXML
    private TableView torneiTV;

    @FXML
    private TableView partecipantiTV;
    @FXML
    private TableColumn<Persona, Integer> idCol;
    @FXML
    private TableColumn<Persona, String> nomeCol;
    @FXML
    private TableColumn<Persona, String> cognomeCol;
    @FXML
    private TableColumn<Persona, Integer> livelloCol;
    @FXML
    private TableColumn<Persona, String> telefonoCol;


    @FXML
    private TableColumn<Torneo, String> nomeTCol;
    @FXML
    private TableColumn<Torneo, String> dataCol;
    @FXML
    private TableColumn<Torneo, String> oraCol;
    @FXML
    private TableColumn<Torneo, Double> prezzoCol;
    @FXML
    private TableColumn<Torneo, Integer> etaMinCol;
    @FXML
    private TableColumn<Torneo, Integer> numMinCol;
    @FXML
    private TableColumn<Torneo, String> scadenzaCol;
    @FXML
    private TableColumn<Torneo, String> pagamentoCol;

    @FXML
    private Label numIscritti;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTornei();
        setPartecipanti();
    }

    public void getTorneiByRenterId() throws SQLException {
        List<Torneo> tornei;

        tornei = gestisciTorneiRenterBean.getTorneiByRenterId(persona.getId());
        ObservableList<Torneo> values = FXCollections.observableArrayList();
        values.addAll(tornei);
        torneiTV.setItems(values);
        torneiTV.refresh();
    }

    public void setTornei(){
        nomeTCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("nome"));
        dataCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("data"));
        oraCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("ora"));
        prezzoCol.setCellValueFactory(new PropertyValueFactory<Torneo,Double>("prezzo"));
        etaMinCol.setCellValueFactory(new PropertyValueFactory<Torneo,Integer>("etaMin"));
        numMinCol.setCellValueFactory(new PropertyValueFactory<Torneo,Integer>("numMinPart"));
        scadenzaCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("dataScadenza"));
        pagamentoCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("metodoPagamento"));
    }

    public void setPartecipanti(){
        idCol.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<Persona,String>("nome"));
        cognomeCol.setCellValueFactory(new PropertyValueFactory<Persona,String>("cognome"));
        livelloCol.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("livello"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<Persona,String>("telefono"));
    }

    public void indietro() throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePageRenter.fxml"));
        Parent root = (Parent) loader.load();
        HomePageRenterController homePageRenterController = loader.getController();
        homePageRenterController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void getIscritti() throws SQLException {
        if(torneiTV.getSelectionModel().getSelectedItem() == null)
            return;
        partecipantiTV.getItems().clear();
        Torneo torneo = (Torneo) torneiTV.getSelectionModel().getSelectedItem();
        List<Persona> iscritti = gestisciTorneiRenterBean.getIscrittiByTorneoId(torneo.getId());
        partecipantiTV.getItems().addAll(iscritti);
        numIscritti.setText("ISCRITTI : " + iscritti.size());


    }


    public void confermaIscrizione() throws SQLException {
        if(partecipantiTV.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE SELEZIONE UTENTE");
            alert.setContentText("Non Ã¨ stato selezionato nessun utente, per favore riprova.");
            alert.showAndWait();
            return;
        }
        Persona personam = (Persona) partecipantiTV.getSelectionModel().getSelectedItem();
        Torneo torneo = (Torneo)  torneiTV.getSelectionModel().getSelectedItem();

        if(gestisciTorneiRenterBean.confermaIscrizione(personam.getId(), torneo.getId())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ISCRIZIONE CONFERMATA");
            alert.setContentText("Iscrizione confermata con successo.");
            alert.showAndWait();

            partecipantiTV.refresh();
            return;
        }

        errMessage();

    }

    public void cancellaIscrizione() throws SQLException {
        if(partecipantiTV.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE SELEZIONE UTENTE");
            alert.setContentText("Non è stato selezionato nessun utente, per favore riprova.");
            alert.showAndWait();
            return;
        }
        Persona personam = (Persona) partecipantiTV.getSelectionModel().getSelectedItem();
        Torneo torneo = (Torneo)  torneiTV.getSelectionModel().getSelectedItem();

        if(gestisciTorneiRenterBean.cancellaIscrizione(personam.getId(), torneo.getId())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ISCRIZIONE CANCELLATA");
            alert.setContentText("Iscrizione cancellata con successo.");
            alert.showAndWait();

            partecipantiTV.refresh();
            return;
        }

        errMessage();


    }
    
    public void errMessage() {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setContentText("Errore nel sistema: contatta un amministratore.");
        alert.showAndWait();
    	
    }
}
