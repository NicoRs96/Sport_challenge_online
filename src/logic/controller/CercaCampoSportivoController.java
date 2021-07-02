package controller;

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

import bean.CercaCampoBean;
import dao.CercaCampoDao;
import exception.ConnectionClosedFXException;
import model.Persona;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

public class CercaCampoSportivoController implements Initializable {
    
	private Persona persona;

	private final CercaCampoDao cercaCampoDao = new CercaCampoDao();

	private final CercaCampoBean cercaCampoBean = new CercaCampoBean();
	
	@FXML
	private Button esciBTN;
		
    @FXML
    private ComboBox sportComboBox;


    @FXML
    private DatePicker dataDPCCSC;

    @FXML
    private TextField cittaTFCCSC;

    @FXML
    private TableView<Campo> campiTV;

    @FXML
    private TableColumn<Campo, String> nomeCol;
    @FXML
    private TableColumn<Campo, String> comuneColCCSC;
    @FXML
    private TableColumn<Campo, String> indirizzoColCCSC;
    @FXML
    private TableColumn<Campo, String> descColCCSC;
    @FXML
    private TableColumn<Campo, String> dataColCCSC;
    @FXML
    private TableColumn<Campo, String> oraColCCSC;
    @FXML
    private TableColumn<Campo, String> renterCol;
    @FXML
    private TableColumn<Campo, String> sportCol;
    
    @FXML 
    private void indietro(ActionEvent event) throws IOException {

        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomepageSportman.fxml"));
        Parent root =  loader.load();
        HomePageSportmanController homePageSportmanController = loader.getController();
        homePageSportmanController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @FXML
    private void cerca(ActionEvent event) throws SQLException, ParseException, ConnectionClosedFXException, ClassNotFoundException {

    	campiTV.getItems().clear();

        if(cittaTFCCSC.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE CITTA");
            alert.setContentText("Seleziona una citta.");
            alert.showAndWait();
            return;
        }

        if(dataDPCCSC == null || dataDPCCSC.getValue() == null){
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

        cercaCampoBean.setCitta(cittaTFCCSC.getText().trim());
        cercaCampoBean.setData(dataDPCCSC.getValue());
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

        ObservableList<Campo> values = FXCollections.
                observableArrayList();
        
        
        SortedMap<String, TreeMap<String, String>> campos = cercaCampoDao.getCampo(cittaTFCCSC.getText().trim().toUpperCase(), sportComboBox.getValue().toString().toUpperCase(), dataDPCCSC.getValue().toString());
        for (Map.Entry<String, TreeMap<String,String>> entry : campos.entrySet()) {
        	
            TreeMap<String, String> info = entry.getValue();
            String nome = entry.getKey();
            int id = Integer.parseInt(info.get("ID"));
            String comune = info.get("COMUNE");
            String indirizzo = info.get("INDIRIZZO");
            String desc = info.get("DESC");
            String renter = info.get("RENTER");
            String date = info.get("DATA");
            String ora = info.get("ORA");
            
            String prezzo = info.get("PREZZO");
            String modPpagamento = info.get("METODODIPAGAMENTO");

            int isAffittabile = Integer.parseInt(info.get("AFFITTABILE"));
            
            Campo campo = new Campo(id,nome, comune, indirizzo,renter, isAffittabile);
            campo.setDesc(desc);
            campo.setData(date);
            campo.setOra(ora);
            campo.setSport(sportComboBox.getValue().toString());
            campo.setPrezzo(prezzo);
            campo.setModPagamento(modPpagamento);

            if(campo.getIsAffittabile() == 1) {
                values.add(campo);
                campiTV.setItems(values);
            }
        }


    }


    public void prenota() throws SQLException, IOException {

        Campo campo = (Campo) campiTV.getSelectionModel().getSelectedItem();

        if(campo == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE CAMPO");
            alert.setContentText("Attenzione, campo non selezioanto.");
            alert.showAndWait();
            return;
        }

        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConfermaPrenotazioneCampo.fxml"));
        Parent root =  loader.load();
        ConfermaPrenotazioneCampoController confermaPrenotazioneCampoController = loader.getController();
        confermaPrenotazioneCampoController.setPersona(persona);
        confermaPrenotazioneCampoController.setCampo(campo);
        confermaPrenotazioneCampoController.fill();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("nome"));
        comuneColCCSC.setCellValueFactory(new PropertyValueFactory<Campo,String>("comune"));
        indirizzoColCCSC.setCellValueFactory(new PropertyValueFactory<Campo,String>("indirizzo"));
        descColCCSC.setCellValueFactory(new PropertyValueFactory<Campo,String>("desc"));
        dataColCCSC.setCellValueFactory(new PropertyValueFactory<Campo,String>("data"));
        oraColCCSC.setCellValueFactory(new PropertyValueFactory<Campo,String>("ora"));
        renterCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("renter"));
        sportCol.setCellValueFactory(new PropertyValueFactory<Campo,String>("sport"));
    }

    public void setPersona(Persona persona){
        this.persona = persona;
    }

}
