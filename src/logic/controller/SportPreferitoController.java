package controller;

import bean.SportPreferitoBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Persona;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SportPreferitoController implements Initializable {
    private SportPreferitoBean sportPreferitoBean = new SportPreferitoBean();
    private Persona persona;

    @FXML
    ComboBox sportComboBox;

    @FXML
    Button esciBTN;


    @FXML
    Button confermaBTN;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//nothing
    }


    public void indietro() throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomepageSportman.fxml"));
        Parent root = (Parent) loader.load();
        HomePageSportmanController homePageSportmanController = loader.getController();
        homePageSportmanController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void conferma() throws SQLException {
        if(sportComboBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE INSERIMENTO DATI");
            alert.setContentText("Attenzione,nessuno sport selezionato.");
            alert.showAndWait();
            return;
        }

        if(sportPreferitoBean.setSportPreferito(persona.getId(), sportComboBox.getValue().toString().toUpperCase())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("COMPLETATO");
            String mdg = String.format("Complimenti, %s e' il tuo sport preferito!", sportComboBox.getValue().toString());
            alert.setContentText(mdg);
            alert.showAndWait();
        }
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
