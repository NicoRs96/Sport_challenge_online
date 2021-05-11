package controller;

import bean.ScegliLivelloBean;
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
import java.util.ResourceBundle;

public class ScegliLivelloController implements Initializable {
    private final ScegliLivelloBean scegliLivelloBean = new ScegliLivelloBean();
    private Persona persona;

    @FXML
    ToggleGroup test;

    @FXML
    ComboBox sportComboBox;

    @FXML
    Button esciBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//nothing

    }

    public void indietro() throws IOException {
        Stage stageSLC = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loaderSLC = new FXMLLoader(getClass().getResource("/view/HomepageSportman.fxml"));
        Parent rootSLC = loaderSLC.load();
        HomePageSportmanController homePageSportmanController = loaderSLC.getController();
        homePageSportmanController.setPersona(persona);
        Scene scene = new Scene(rootSLC);
        stageSLC.setScene(scene);
    }

    public void conferma() throws SQLException {
        if(sportComboBox.getValue() == null || test.getSelectedToggle() == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE INSERIMENTO DATI");
            alert.setContentText("Alcuni campi risultano vuoti, riprova.");
            alert.showAndWait();
            return;
        }
        if(scegliLivelloBean.setLivello(persona.getId(), sportComboBox.getValue().toString().toUpperCase(), ((RadioButton) test.getSelectedToggle()).getText().toUpperCase())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("COMPLETATO");
            String mdg = String.format("Complimenti, hai inserito il livello %s per %s!", ((RadioButton) test.getSelectedToggle()).getText(),
                    sportComboBox.getValue().toString());
            alert.setContentText(mdg);
            alert.showAndWait();
        }
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
