package controller;

import bean.CercaCampoBean;
/*import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;*/
import entity.Campo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class CercaCampoController implements Initializable {
    private CercaCampoBean cercaCampoBean = new CercaCampoBean();

    @FXML
    private ComboBox sportComboBox;


    @FXML
    private DatePicker DataDP;

    @FXML
    private TextField CittaTF;

    @FXML
    private TableView CampiTV;

    @FXML
    private TableColumn<Campo, String> nomeCol;
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
    private TableColumn<Campo, String> renterCol;
    @FXML
    private TableColumn<Campo, String> sportCol;


    @FXML
    private void cerca() throws SQLException, ParseException {

        CampiTV.getItems().clear();

        if(CittaTF.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE CITTA");
            alert.setContentText("Seleziona una citta.");
            alert.showAndWait();
            return;
        }

        if(DataDP == null || DataDP.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE DATA");
            alert.setContentText("Seleziona una data.");
            alert.showAndWait();
            return;
        }

        if(sportComboBox.getValue().equals("Seleziona uno sport"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE SPORT");
            alert.setContentText("Seleziona uno sport.");
            alert.showAndWait();
            return;
        }

        // DATA PASSATA
        if(DataDP.getValue().getYear() < LocalDateTime.now().getYear() ||
                (DataDP.getValue().getYear() == LocalDateTime.now().getYear() && DataDP.getValue().getMonth().getValue() < LocalDateTime.now().getMonth().getValue()) ||
                (DataDP.getValue().getYear() == LocalDateTime.now().getYear()
                        && DataDP.getValue().getMonth() == LocalDateTime.now().getMonth()
                        && DataDP.getValue().getDayOfMonth() < LocalDateTime.now().getDayOfMonth()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE DATA");
            alert.setContentText("Non puoi cercare un campo per una data passata.");
            alert.showAndWait();
            return;
        }

        if(!cercaCampoBean.isCityAvailable(CittaTF.getText().trim())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE CITTA");
            alert.setContentText("La citta inserita non Esiste.");
            alert.showAndWait();
            return;
        }

        TreeMap<String, TreeMap<String, String>> campos = cercaCampoBean.getCampo(CittaTF.getText().trim().toUpperCase(), sportComboBox.getValue().toString().toUpperCase(), DataDP.getValue().toString());
        for (String name : campos.keySet()) {
            TreeMap<String, String> info = campos.get(name);
            String nome = name;
            String comune = info.get("COMUNE");
            String indirizzo = info.get("INDIRIZZO");
            String desc = info.get("DESC");
            String renter = info.get("RENTER");
            String date = info.get("DATA");
            String ora = info.get("ORA");

            Campo campo = new Campo(nome, comune, indirizzo, desc, date, ora, renter, sportComboBox.getValue().toString());

            CampiTV.getItems().add(campo);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("nome"));
        comuneCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("comune"));
        indirizzoCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("indirizzo"));
        descCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("desc"));
        dataCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("data"));
        oraCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("ora"));
        renterCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("renter"));
        sportCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("sport"));
    }
}
