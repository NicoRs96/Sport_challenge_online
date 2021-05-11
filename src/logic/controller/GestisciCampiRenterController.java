package controller;

import bean.GestisciCampiBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Campo;
import model.Persona;
import model.Prenotazione;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class GestisciCampiRenterController implements Initializable {
    private final GestisciCampiBean gestisciCampiBean = new GestisciCampiBean();

    @FXML
    private Button esciBTN;
    @FXML
    private TableColumn<Campo, String> nomeCol;
    @FXML
    private TableColumn<Campo, String> comuneCol;
    @FXML
    private TableColumn<Campo, String> indirizzoCol;
    @FXML
    private TableColumn<Campo, String> descCol;
    @FXML
    private TableColumn<Campo, String> dataCol1;
    @FXML
    private TableColumn<Campo, String> oraCol1;
    @FXML
    private TableColumn<Campo, String> renterCol;
    @FXML
    private TableColumn<Campo, String> sportCol;

    @FXML
    private TableColumn<Prenotazione, String> campoCol;

    @FXML
    private TableColumn<Prenotazione, String> dataCol;

    @FXML
    private TableColumn<Prenotazione, String> oraCol;

    @FXML
    private TableColumn<Prenotazione, String> prezzoCol;

    @FXML
    private TableColumn<Prenotazione, String> nomeClienteCol;

    @FXML
    private TableColumn<Prenotazione, String> cognomeClienteCol;

    @FXML
    private TableColumn<Prenotazione, String> telefonoClienteCol;

    @FXML
    private TableView campiTV;

    @FXML
    private TableView prenotazioniTV;

    private Persona persona;
    
	String campoErratoString = "Errore selezione campo";
	String successString = "SUCCESS";




    public void getCampi() throws SQLException, ParseException {

        ObservableList<Campo> values = FXCollections.
                observableArrayList();

        int renterId = persona.getId();
        TreeMap<Integer, ArrayList<TreeMap<String, String>>> campos = gestisciCampiBean.getCampi(renterId);
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

        campiTV.setRowFactory(tv-> new TableRow<Campo>(){
            protected void updateItem(Campo campo, boolean empty) {
                super.updateItem(campo, empty);
                if (campo == null)
                    setStyle("");
                else if (campo.getIsAffittabile() == 0)
                    setStyle("-fx-background-color: LightCoral");
                else
                    setStyle("-fx-background-color: lightgreen;");
            }
        });
    }

    public void getPrenotazioni() throws SQLException {
        ObservableList<Prenotazione> values = FXCollections.
                observableArrayList();

        TreeMap<Integer, TreeMap<String, String>> prenotazioni = gestisciCampiBean.getPrenotazioni(persona.getId());
        for (int id : prenotazioni.keySet()) {
            TreeMap<String, String> info = prenotazioni.get(id);
            String campo = info.get("CAMPO");
            String data = info.get("DATA");
            String ora = info.get("ORA");
            String prezzo = info.get("PREZZO");
            String nomeCliente = info.get("NOMECLIENTE");
            String cognomeCliente = info.get("COGNOMECLIENTE");
            String telefono = info.get("TELEFONO");

            Prenotazione prenotazione = new Prenotazione(id, campo, data, ora, prezzo, nomeCliente, cognomeCliente);
            prenotazione.setTelefonoCliente(telefono);
            values.add(prenotazione);
            prenotazioniTV.setItems(values);
        }

    }

    @FXML
    public void rendiAffittabile(ActionEvent event) throws SQLException {
    	

        if(campiTV.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(campoErratoString);
            alert.setContentText("Non hai selezionato nessun campo, riprova.");
            alert.showAndWait();
            return;
        }

        Campo campo = (Campo) campiTV.getSelectionModel().getSelectedItem();
        if(campo.getIsAffittabile() == 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(campoErratoString);
            alert.setContentText("Il campo selezionato è già affittabile.");
            alert.showAndWait();
            return;
        }

        if(gestisciCampiBean.setCampoAffittabile(campo.getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(successString);
            alert.setContentText("Il campo selezionato adesso è affittabile.");
            alert.showAndWait();
            ricarica();
        }


    }


    public void cancellaAffittabilita() throws SQLException {
        Campo campo = (Campo) campiTV.getSelectionModel().getSelectedItem();
        if(campiTV.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(campoErratoString);
            alert.setContentText("Non hai selezionato nessun campo, riprova.");
            alert.showAndWait();
            return;
        }

        if(campo.getIsAffittabile() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(campoErratoString);
            alert.setContentText("Il campo selezionato non è affittabile.");
            alert.showAndWait();
            return;
        }

        if(gestisciCampiBean.setCampoNonAffittabile(campo.getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(successString);
            alert.setContentText("Il campo selezionato adesso non è più affittabile.");
            alert.showAndWait();
            ricarica();
        }
    }

    public void cancellaPrenotazione()throws SQLException{
       if(prenotazioniTV.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE SELEZIONE PRENOTAZIONE");
            alert.setContentText("Non hai selezionato nessuna prenotazione, riprova.");
            alert.showAndWait();
            return;
        }

        Prenotazione prenotazione = (Prenotazione) prenotazioniTV.getSelectionModel().getSelectedItem();
        if(gestisciCampiBean.cancellaPrenotazione(prenotazione.getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("La prenotazione è stata cancellata.");
            alert.showAndWait();
            ricarica();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCampi();
        setPrenotazioni();
    }

    public void setCampi(){
        nomeCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("nome"));
        comuneCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("comune"));
        indirizzoCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("indirizzo"));
        descCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("desc"));
        dataCol1.setCellValueFactory(new PropertyValueFactory<Campo,String>("data"));
        oraCol1.setCellValueFactory(new PropertyValueFactory<Campo,String>("ora"));
        sportCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("sport"));
    }
    public void setPrenotazioni(){
        campoCol.setCellValueFactory(new PropertyValueFactory<Prenotazione,String>("nomeCampo"));
        dataCol.setCellValueFactory(new PropertyValueFactory<Prenotazione,String>("data"));
        oraCol.setCellValueFactory(new PropertyValueFactory<Prenotazione,String>("ora"));
        prezzoCol.setCellValueFactory(new PropertyValueFactory<Prenotazione,String>("prezzo"));
        nomeClienteCol.setCellValueFactory(new PropertyValueFactory<Prenotazione,String>("nomeCliente"));
        cognomeClienteCol.setCellValueFactory(new PropertyValueFactory<Prenotazione,String>("cognomeCliente"));
        telefonoClienteCol.setCellValueFactory(new PropertyValueFactory<Prenotazione,String>("telefonoCliente"));
    }

    public void ricarica() {
        try {
            getCampi();
            getPrenotazioni();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void setPersona(Persona persona){
        this.persona = persona;
    }


    public void esci() throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePageRenter.fxml"));
        Parent root = loader.load();
        HomePageRenterController homePageRenterController = loader.getController();
        homePageRenterController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


}
