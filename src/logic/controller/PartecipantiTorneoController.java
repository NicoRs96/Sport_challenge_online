package controller;

import bean.PartecipantiTorneoBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Campo;
import model.Persona;
import model.Torneo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PartecipantiTorneoController implements Initializable {
    private Persona persona;
    private Torneo torneo;
    private List<Persona> iscritti;
    private final PartecipantiTorneoBean partecipantiTorneoBean = new PartecipantiTorneoBean();

    @FXML
    private Button esciBTN;
    @FXML
    private Text torneoTXT;
    @FXML
    private Text dataTXT;
    @FXML
    private Text oraTXT;
    @FXML
    private Text partecipantiTXT;
    @FXML
    private TextField amicoTF;
    @FXML
    private Text meteoTXT;
    @FXML
    private Text tMinTXT;
    @FXML
    private Text tMaxTXT;

    @FXML
    private TableView partecipantiTV;
    @FXML
    private TableColumn<Persona, Integer> idCol;
    @FXML
    private TableColumn<Persona, String> nomeCol;
    @FXML
    private TableColumn<Persona, String> cognomeCol;
    @FXML
    private TableColumn<Persona, Integer> livelloCol;

    @FXML
    private ImageView soleIV;
    @FXML
    private ImageView nuvoleIV;
    @FXML
    private ImageView pioggiaIV;

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public void setPartecipanti() {
        idCol.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<Persona, String>("nome"));
        cognomeCol.setCellValueFactory(new PropertyValueFactory<Persona, String>("cognome"));
        livelloCol.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("livello"));
    }

    public void setInfo() throws SQLException, IOException {
        torneoTXT.setText(torneo.getNome());
        dataTXT.setText(torneo.getData().toString());
        oraTXT.setText(torneo.getOra());
        getMeteo(torneo);

        getIscritti();
    }

    public void getIscritti() throws SQLException {
        partecipantiTV.getItems().clear();
        iscritti = partecipantiTorneoBean.getIscrittiByTorneoId(torneo);
        partecipantiTV.getItems().addAll(iscritti);
        partecipantiTXT.setText("PARTECIPANTI: " + iscritti.size());
    }

    public void indietro(ActionEvent event) throws IOException {
        Stage stage = (Stage) esciBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CercaTorneo.fxml"));
        Parent root = loader.load();
        CercaTorneoController cercaTorneoController = loader.getController();
        cercaTorneoController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        soleIV.setVisible(false);
        nuvoleIV.setVisible(false);
        pioggiaIV.setVisible(false);
        tMinTXT.setVisible(false);
        tMaxTXT.setVisible(false);
        meteoTXT.setVisible(false);
        setPartecipanti();
    }

    public void invita(MouseEvent mouseEvent) throws SQLException {
        if (amicoTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Inserisci un'email.");
            alert.showAndWait();
            return;
        }
        String email = amicoTF.getText();

        Persona invitato = partecipantiTorneoBean.getPersonaByEmail(email);
        if (invitato == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Nessun utente trovato con questa email.");
            alert.showAndWait();
            return;
        }

        if (iscritti.stream().filter(x -> x.getId() == invitato.getId()).findAny().isPresent()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("L'utente è gia iscritto a questo torneo.");
            alert.showAndWait();
            return;
        }

        boolean result = partecipantiTorneoBean.sendInvite(persona, torneo, invitato);


        if (result) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ATTENZIONE");
            alert.setContentText("L'utente è stato invitato con successo.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE");
        alert.setContentText("Hai gia invitato questo utente al torneo.");
        alert.showAndWait();
        return;
    }

    public void iscriviti(MouseEvent mouseEvent) throws SQLException {

        int numIscritti = iscritti.size();
        Campo campo = partecipantiTorneoBean.getCampoById(torneo.getCampoId());
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
        if ((alert.getResult() == ButtonType.OK) &&
                (partecipantiTorneoBean.confermaIscrizione(persona.getId(), torneo.getId()))) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("Iscrizione avvenuta con successo");
            alert.showAndWait();
            getIscritti();
        }
    }

    public void getMeteo(Torneo torneo) throws IOException {
        Document doc = Jsoup.connect("https://www.ilmeteo.it/meteo/" + torneo.getCitta()).get();
        if (doc == null) {
            meteoTXT.setVisible(true);
            meteoTXT.setText("METEO NON DISPONIBILE");
            meteoTXT.setLayoutX(20);
            meteoTXT.setLayoutY(80);
            return;
        }
        Elements newsHeadlines = doc.getElementsByTag("li");
        for (Element headline : newsHeadlines) {
            if (headline.text().split(" ").length > 2) {
                String tmax = headline.getElementsByClass("tmax").text();
                String tmin = headline.getElementsByClass("tmin").text();
                String giorno = headline.getElementsByTag("span").first() != null ? headline.getElementsByTag("span").first().text() : "";
                boolean rain  = headline.getElementsByClass("s flag_pioggia").isEmpty();
                boolean nuvoloso  = headline.getElementsByClass("s ss3").isEmpty();
                boolean sole  = headline.getElementsByClass("s ss1").isEmpty();
                if(tmax.isEmpty() || tmin.isEmpty())
                    continue;

                if(torneo.getData().getMonth() == LocalDate.now().getMonth() && torneo.getData().getDayOfMonth() <= LocalDate.now().getDayOfMonth()+15) {
                    if(giorno.split(" ")[1].equals(""+torneo.getData().getDayOfMonth())) {
                        if(!rain)
                            pioggiaIV.setVisible(true);
                        else if(!nuvoloso)
                            nuvoleIV.setVisible(true);
                        else if(!sole)
                            soleIV.setVisible(true);
                        tMinTXT.setVisible(true);
                        tMaxTXT.setVisible(true);
                        tMinTXT.setText("Temperatura minima: " + tmin);
                        tMaxTXT.setText("Temperatura massima: " + tmax);
                        meteoTXT.setVisible(true);
                        meteoTXT.setText("METEO: " + torneo.getCitta());
                        return;
                    }
                }
            }
        }
        meteoTXT.setVisible(true);
        meteoTXT.setText("METEO NON DISPONIBILE");
        meteoTXT.setLayoutX(20);
        meteoTXT.setLayoutY(80);
    }

}
