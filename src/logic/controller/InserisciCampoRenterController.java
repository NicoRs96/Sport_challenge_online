package controller;

import bean.InserisciCampoBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Persona;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class InserisciCampoRenterController implements Initializable {
    private Persona persona;

    @FXML
    Button esciBTN;

    @FXML
    TextField nomeCampoTF;
    @FXML
    TextField indirizzoTF;
    @FXML
    TextField cittaTF;
    @FXML
    TextField descrizioneTF;
    @FXML
    ComboBox sportComboBox;
    @FXML
    TextField oraTF;
    @FXML
    TextField prezzoS;
    @FXML
    DatePicker dataDPICRC;
    @FXML
    ToggleGroup metodoTG;
    @FXML
    CheckBox torneoCB;

    @FXML
    Button confermaBTN;

    private InserisciCampoBean inseriscoCampoBean = new InserisciCampoBean();

    @FXML
    public void indietro(ActionEvent event) {
    	//nothing
    }

    public InserisciCampoRenterController(){
    	//constructor
    }


    public void confermaRegistrazione() throws SQLException {

        if(nomeCampoTF.getText().trim().isEmpty() ||
                indirizzoTF.getText().trim().isEmpty() ||
                cittaTF.getText().trim().isEmpty() ||
                descrizioneTF.getText().trim().isEmpty() ||
                oraTF.getText().trim().isEmpty() ||
                prezzoS.getText().trim().isEmpty() ||
                dataDPICRC.getValue() == null ||
                sportComboBox.getValue() == null ||
                metodoTG.getSelectedToggle() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE INSERIMENTO DATI");
            alert.setContentText("Alcuni campi risultano vuoti, riprova.");
            alert.showAndWait();
            return;
        }


        if(dataDPICRC.getValue().getYear() < LocalDateTime.now().getYear() ||
                (dataDPICRC.getValue().getYear() == LocalDateTime.now().getYear() && dataDPICRC.getValue().getMonth().getValue() < LocalDateTime.now().getMonth().getValue()) ||
                (dataDPICRC.getValue().getYear() == LocalDateTime.now().getYear()
                        && dataDPICRC.getValue().getMonth() == LocalDateTime.now().getMonth()
                        && dataDPICRC.getValue().getDayOfMonth() < LocalDateTime.now().getDayOfMonth()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE DATA");
            alert.setContentText("Non puoi inserire un campo per una data passata.");
            alert.showAndWait();
            return;
        }
        RadioButton selectedRadioButton = (RadioButton) metodoTG.getSelectedToggle();

        String torneo = ( torneoCB.isSelected()) ? "1" : "0" ;
        for (String ora: oraTF.getText().split(" ")) {
                if(inseriscoCampoBean.inserisciCampo(nomeCampoTF.getText().trim(),
                        cittaTF.getText().trim(),
                        indirizzoTF.getText().trim(),
                        sportComboBox.getValue().toString(),
                        descrizioneTF.getText().trim(),
                        persona.getId(),
                        dataDPICRC.getValue().toString(),
                        ora,
                        selectedRadioButton.getText(),
                        prezzoS.getText(), torneo)){
                	//nothing
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRORE INSERIMENTO CAMPO");
                    alert.setContentText("C'è stato un errore nell'inserimento del campo, riprova più tardi.");
                    alert.showAndWait();
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("COMPLETATO");
            alert.setContentText("Complimenti, hai inserito il/i tuoi/i campi con successo!");
            alert.showAndWait();
        }

    public void esci() throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePageRenter.fxml"));
        Parent root = (Parent) loader.load();
        HomePageRenterController homePageRenterController = loader.getController();
        homePageRenterController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//nothing
    }

    public void setPersonas(Persona persona){
        this.persona = persona;
    }
}
