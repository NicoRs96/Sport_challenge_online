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
import java.util.ResourceBundle;
import java.util.TreeMap;

public class CercaTorneoController implements Initializable {
    private CercaTorneoBean cercaTorneoBean = new CercaTorneoBean();
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



        TreeMap<Integer, TreeMap<String, String>> tornei = cercaTorneoBean.getTornei(cittaTF.getText().trim().toUpperCase(), dataDP.getValue().toString());
        for (Integer id : tornei.keySet()) {
            TreeMap<String, String> info = tornei.get(id);
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
            String campo = cercaTorneoBean.getCampoById(campoId).getNome();
            Torneo torneo = new Torneo(id, nome,campo, date, ora, Double.parseDouble(prezzo), Integer.parseInt(eta),Integer.parseInt(numMin), dataS, mod, desc);
            torneo.setCampoId(campoId);
            torneiTV.getItems().add(torneo);
        }
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void iscriviti() throws SQLException {
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

        int numIscritti = cercaTorneoBean.getNumIscritti(torneo.getId());
        Campo campo = cercaTorneoBean.getCampoById(torneo.getCampoId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RIEPILOGO ISCRIZIONE");
        String information = String.format("Riepilogo:\n----------\nNome Torneo: %s\n----------\nNumero Iscritti: %s\n----------\nCitta: %s\nPrezzo: %s\nModalita di pagamento: %s\nData: %s\nOra: %s\nSport: %s",
                torneo.getNome(),
                numIscritti,
                campo.getComune(),
                torneo.getPrezzo(),
                torneo.getMetodoPagamento(),
                torneo.getData(),
                torneo.getOra(),
                campo.getSport());
        alert.setContentText(information + "\n\nPREMERE OK per CONFERMARE");
        alert.showAndWait();
        if((alert.getResult() == ButtonType.OK)&&
            (cercaTorneoBean.confermaIscrizione(persona.getId(),torneo.getId()))) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS");
                alert.setContentText("Iscrizione avvenuta con successo");
                alert.showAndWait();
                torneiTV.getItems().remove(torneiTV.getSelectionModel().getSelectedItem());
            }
        


    }
}
