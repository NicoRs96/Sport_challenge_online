package controller;

import bean.CercaTorneoBean;
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
import model.Torneo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

public class CercaTorneoController implements Initializable {
    private final CercaTorneoBean cercaTorneoBean = new CercaTorneoBean();
    private Persona persona;

    @FXML
    private Button esciBTN;

    @FXML
    private DatePicker dataDP;

    @FXML
    private TextField cittaTF;

    @FXML
    private TableView torneiTV;

    @FXML
    private Button confermaBTN;

    @FXML
    private TableColumn<Torneo, String> nomeCol;
    @FXML
    private TableColumn<Torneo, String> campoCol;
    @FXML
    private TableColumn<Torneo, String> dataCol;
    @FXML
    private TableColumn<Torneo, String> oraCol;
    @FXML
    private TableColumn<Torneo, String> etaMinCol;
    @FXML
    private TableColumn<Torneo, String> numPartMin;
    @FXML
    private TableColumn<Torneo, String> dataScadenzaCol;
    @FXML
    private TableColumn<Torneo, String> prezzoCol;
    @FXML
    private TableColumn<Torneo, String> metodoPagCol;
    @FXML
    private TableColumn<Torneo, String> descCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("nome"));
        campoCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("campo"));
        dataCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("data"));
        oraCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("ora"));
        prezzoCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("prezzo"));
        etaMinCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("etaMin"));
        numPartMin.setCellValueFactory(new PropertyValueFactory<Torneo,String>("numMinPart"));
        dataScadenzaCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("dataScadenza"));
        metodoPagCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("metodoPagamento"));
        descCol.setCellValueFactory(new PropertyValueFactory<Torneo,String>("desc"));
    }

    public void indietro() throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomepageSportman.fxml"));
        Parent root =  loader.load();
        HomePageSportmanController homePageSportmanController = loader.getController();
        homePageSportmanController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    public void cercaTorneiByCityAndDate() throws SQLException {
        torneiTV.getItems().clear();
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

        if(dataDP.getValue().isBefore(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE DATA");
            alert.setContentText("Non puoi cercare un torneo per una data passata.");
            alert.showAndWait();
            return;
        }



        SortedMap<Integer, TreeMap<String, String>> tornei = cercaTorneoBean.getTornei(cittaTF.getText().trim().toUpperCase(), dataDP.getValue().toString());
        for (Map.Entry<Integer, TreeMap<String, String>> entry : tornei.entrySet()) {
            
        	Integer id = entry.getKey();
        	TreeMap<String, String> info = entry.getValue();
            String nome = info.get("NOME");

          
            String desc = info.get("DESC");
            String date = info.get("DATA");
            String ora = info.get("ORA");
            String eta = info.get("ETA");
            String numMin = info.get("NUMEROMIN");
            String dataS = info.get("DATASCADENZA");
            String prezzo = info.get("PREZZO");
            String mod = info.get("METODODIPAGAMENTO");

            int campoId = Integer.parseInt(info.get("CAMPO"));
            Campo campo = cercaTorneoBean.getCampoById(campoId);
            String nomeCampo = campo.getNome();
            Torneo torneo = new Torneo(nome, nomeCampo, LocalDate.parse(date), ora, Double.parseDouble(prezzo), Integer.parseInt(eta),Integer.parseInt(numMin));
            torneo.setId(id);
            torneo.setDataScadenza(dataS);
            torneo.setMetodoPagamento(mod);
            torneo.setDesc(desc);
            torneo.setSport(campo.getSport());
            torneo.setCitta(campo.getComune());
          
            torneo.setCampoId(campoId);
            torneiTV.getItems().add(torneo);
        }
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void conferma() throws SQLException, IOException {
        Torneo torneo = (Torneo) torneiTV.getSelectionModel().getSelectedItem();

        if(torneo == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE TORNEO");
            alert.setContentText("Attenzione, torneo non selezioanto.");
            alert.showAndWait();
            return;
        }

        if(torneo.getEtaMin() > Period.between(this.persona.getDataPersona(), LocalDate.now()).getYears()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE ETA");
            alert.setContentText("Attenzione, età minima torneo superiore alla tua.");
            alert.showAndWait();
            return;
        }

        if(LocalDate.parse(torneo.getDataScadenza()).isBefore(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERRORE DATA SCADENZA");
            alert.setContentText("Attenzione, le iscrizioni sono chiuse.");
            alert.showAndWait();
            return;
        }

        Stage stage = (Stage) confermaBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PartecipantiTorneo.fxml"));
        Parent root = loader.load();
        PartecipantiTorneoController partecipantiTorneoController = loader.getController();
        partecipantiTorneoController.setPersona(persona);
        partecipantiTorneoController.setTorneo(torneo);
        partecipantiTorneoController.setInfo();
        Scene scene = new Scene(root);
        stage.setScene(scene);



    }
}
