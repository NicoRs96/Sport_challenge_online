package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Campo;
import view.HomePageSportmanView;

import javax.swing.*;
import javax.xml.crypto.Data;

import bean.CercaCampoBean;
import dao.CercaCampoDao;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class CercaCampoSportivoController implements Initializable {
    
	
	private CercaCampoDao cercaCampoDao = new CercaCampoDao();

	private CercaCampoBean cercaCampoBean = new CercaCampoBean();
	
	@FXML
	private Button esciBTN;
		
    @FXML
    private ComboBox sportComboBox;


    @FXML
    private DatePicker dataDP;

    @FXML
    private TextField cittaTF;

    @FXML
    private TableView campiTV;

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
    private void indietro(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) esciBTN.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void cerca(ActionEvent event) throws SQLException, ParseException {

    	campiTV.getItems().clear();

        if(cittaTF.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE CITTA");
            alert.setContentText("Seleziona una citta.");
            alert.showAndWait();
            return;
        }

        if(dataDP == null || dataDP.getValue() == null){
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

        cercaCampoBean.setCittà(cittaTF.getText().trim());
        cercaCampoBean.setData(dataDP.getValue());
        cercaCampoBean.setSport(sportComboBox.getValue().toString());
        
        // DATA PASSATA
        if (cercaCampoBean.checkData()== 1 )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE DATA");
            alert.setContentText("Non puoi cercare un campo per una data passata.");
            alert.showAndWait();
            return;
        }

        if(!cercaCampoBean.isCityAvailable()){
        	
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE CITTA");
            alert.setContentText("La citta inserita non Esiste.");
            alert.showAndWait();
            return;
        }

        Label lblValue = new Label();
        ObservableList<Campo> values = FXCollections.
                observableArrayList();
        
        
        TreeMap<String, TreeMap<String, String>> campos = cercaCampoDao.getCampo(cittaTF.getText().trim().toUpperCase(), sportComboBox.getValue().toString().toUpperCase(), dataDP.getValue().toString());
        for (String name : campos.keySet()) {
            TreeMap<String, String> info = campos.get(name);
            String nome = name;
            String comune = info.get("COMUNE");
            String indirizzo = info.get("INDIRIZZO");
            String desc = info.get("DESC");
            String renter = info.get("RENTER");
            String date = info.get("DATA");
            String ora = info.get("ORA");
            
            String prezzo = info.get("PREZZO");
            String modPpagamento = info.get("MOD_PAGAMENTO");
            
            Campo campo = new Campo(nome, comune, indirizzo,renter);
            campo.setDesc(desc);
            campo.setData(date);
            campo.setOra(ora);
            campo.setSport(sportComboBox.getValue().toString());
            campo.setPrezzo(prezzo);
            campo.setModPagamento(modPpagamento);
            
            values.add(campo);
            campiTV.setItems(values);
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
