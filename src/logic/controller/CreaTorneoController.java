package controller;

import bean.CreaTorneoBean;
import bean.InserisciCampoBean;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Campo;
import model.Persona;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreaTorneoController implements Initializable {
    private Persona persona;
    private List<Campo> campi = new ArrayList<>();

    public CreaTorneoController(){
    }

    @FXML
    ComboBox campiComboBox;

    @FXML
    DatePicker dataDP;

    @FXML
    DatePicker datascadenzaDP;

    @FXML
    TextField oraTF;

    @FXML
    TextField nomeTF;

    @FXML
    TextField descTF;

    @FXML
    TextField etaMinimaTF;

    @FXML
    TextField numMinIscrittiTF;

    @FXML
    TextField prezzoTF;

    @FXML
    CheckBox contantiCB;

    @FXML
    CheckBox carteCB;

    @FXML
    Button esciBTN;

    private CreaTorneoBean creaTorneoBean = new CreaTorneoBean();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void indietro(ActionEvent event) throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePageRenter.fxml"));
        Parent root = (Parent) loader.load();
        HomePageRenterController homePageRenterController = loader.getController();
        homePageRenterController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void getCampiByRenterId() throws SQLException {
        this.campi = new CreaTorneoBean().getCampiByRenterId(persona.getId());
        campiComboBox.setItems(FXCollections.observableList((campi).stream().map(x->x.getId()).collect(Collectors.toList())));
    }

    public void setPersona(Persona persona){
        this.persona = persona;
    }

    @FXML
    public void test(ActionEvent event) {
        Alert alert  = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFO");
        Campo campo = campi.stream().filter(x->x.getId() == (int) campiComboBox.getSelectionModel().getSelectedItem()).findFirst().orElse(null);
        alert.setContentText(campo.toString().replace(";","\n"));
        alert.showAndWait();
        return;
    }

    public void conferma(ActionEvent actionEvent) throws SQLException {
        if(oraTF.getText().trim().isEmpty() ||
                prezzoTF.getText().trim().isEmpty() ||
                nomeTF.getText().trim().isEmpty() ||
                descTF.getText().trim().isEmpty() ||
                dataDP.getValue() == null ||
                etaMinimaTF.getText() == null ||
                numMinIscrittiTF.getText() == null ||
                datascadenzaDP.getValue() == null ||
                campiComboBox.getSelectionModel().getSelectedItem().toString().equals("Seleziona un campo")||
                (!carteCB.isSelected() && !contantiCB.isSelected())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE INSERIMENTO DATI");
            alert.setContentText("Alcuni campi risultano vuoti, riprova.");
            alert.showAndWait();
            return;
        }

        if(dataDP.getValue().getYear() < LocalDateTime.now().getYear() ||
                (dataDP.getValue().getYear() == LocalDateTime.now().getYear() && dataDP.getValue().getMonth().getValue() < LocalDateTime.now().getMonth().getValue()) ||
                (dataDP.getValue().getYear() == LocalDateTime.now().getYear()
                        && dataDP.getValue().getMonth() == LocalDateTime.now().getMonth()
                        && dataDP.getValue().getDayOfMonth() < LocalDateTime.now().getDayOfMonth()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE DATA");
            alert.setContentText("Non puoi inserire un campo per una data passata.");
            alert.showAndWait();
            return;
        }

        if(dataDP.getValue().isBefore(datascadenzaDP.getValue())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE DATA");
            alert.setContentText("La data del torneo è più recente della data di scadenza dell'iscrizione.");
            alert.showAndWait();
            return;
        }

        int etaMin = 0;
        try{
            etaMin = Integer.parseInt(etaMinimaTF.getText());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE ETA MIN");
            alert.setContentText("Inserisci un numero valido");
            alert.showAndWait();
            return;
        }

        if(etaMin<14){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE ETA MIN");
            alert.setContentText("Non puoi inserire una età minima inferiore a 14 anni");
            alert.showAndWait();
            return;
        }

        int minPart = 0;
        try{
            minPart = Integer.parseInt(numMinIscrittiTF.getText());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE NUMERO MINIMO ISCRITTI");
            alert.setContentText("Inserisci un numero valido");
            alert.showAndWait();
            return;
        }

        if(minPart<2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE NUMERO MINIMO ISCRITTI");
            alert.setContentText("Il numero minimo di partecipanti è 2.");
            alert.showAndWait();
            return;
        }

        double prezzo = 0.0;
        try{
            prezzo = Double.parseDouble(prezzoTF.getText());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE PREZZO");
            alert.setContentText("Inserisci un numero valido");
            alert.showAndWait();
            return;
        }

        if(prezzo <= 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE PREZZO");
            alert.setContentText("Inserisci un numero > 0");
            alert.showAndWait();
            return;
        }


        String metodo = "";
        if(carteCB.isSelected() && contantiCB.isSelected())
            metodo = "ENTRAMBI";
        else if(carteCB.isSelected())
            metodo = "CARTA DI CREDITO";
        else metodo = "CONTANTI";
        if(creaTorneoBean.inserisciTorneo(nomeTF.getText(), Integer.parseInt(campiComboBox.getSelectionModel().getSelectedItem().toString()),
                dataDP.getValue().toString(), oraTF.getText(),etaMin,
                minPart,datascadenzaDP.getValue().toString(), prezzo,metodo, descTF.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("COMPLETATO");
            alert.setContentText("Complimenti, hai creato con un successo un torneo!");
            alert.showAndWait();
            return;
        }
    }
}
