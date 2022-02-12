package logic.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import logic.bean.CancellaAccountBean;
import logic.bean.InviteNotificationBean;
import logic.model.Invito;
import logic.model.Persona;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;


public class HomePageSportmanController implements Initializable {
    private Persona persona;
    private final CancellaAccountBean cancellaAccountBean = new CancellaAccountBean();
    private final InviteNotificationBean inviteBean = new InviteNotificationBean();
    private String successo = "SUCCESS";

    @FXML
    private Button esciBTN;

    @FXML
    private Button cercaCSBTN;

    @FXML
    private Button cercaTornBTN;

    @FXML
    private Button specificalvlBTN;

    @FXML
    private Button disiscriviBTN;

    @FXML
    private Button sportPrefeBTN;

    @FXML
    private Button iMieiEventiBTN;

    String percorso = "/logic/view/Main.fxml";


    @FXML
    private void indietro(ActionEvent event) throws IOException {

        Stage stage = (Stage) esciBTN.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(percorso));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cercaCampo() throws IOException {
        Stage stage = (Stage) cercaCSBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/CercaCampoSportivo.fxml"));
        Parent root = loader.load();
        CercaCampoSportivoController cercaCampoSportivoController = loader.getController();
        cercaCampoSportivoController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    public void cercaTornei() throws IOException {
        Stage stage = (Stage) cercaTornBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/CercaTorneo.fxml"));
        Parent root = loader.load();
        CercaTorneoController cercaTorneoController = loader.getController();
        cercaTorneoController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void gestisciEventi() throws IOException, SQLException, ParseException {
        Stage stage = (Stage) iMieiEventiBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/GestisciMieiEventi.fxml"));
        Parent root = loader.load();
        GestisciMieiEventiController gestisciMieiEventiController = loader.getController();
        gestisciMieiEventiController.setPersona(persona);
        gestisciMieiEventiController.getCampi();
        gestisciMieiEventiController.getTornei();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void gestisciLivello() throws IOException, SQLException, ParseException {
        Stage stage = (Stage) specificalvlBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/ScegliLivello.fxml"));
        Parent root = loader.load();
        ScegliLivelloController scegliLivelloController = loader.getController();
        scegliLivelloController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void gestisciSportPreferito() throws IOException, SQLException, ParseException {
        Stage stage = (Stage) sportPrefeBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/SportPreferito.fxml"));
        Parent root = loader.load();
        SportPreferitoController sportPreferitoController = loader.getController();
        sportPreferitoController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void cancellaAccountSportman() throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ATTENZIONE");
        alert.setContentText("ATTENZIONE, OPERAZIONE IRREVERSIBILE.\nPREMERE OK per CONFERMARE DEFINITIVAMENTE");
        alert.showAndWait();
        if ((alert.getResult() == ButtonType.OK) &&
                (cancellaAccountBean.deleteAccount(persona.getId()))) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(successo);
            alert.setContentText("ACCOUNT CANCELLATO CON SUCCESSO. A PRESTO");
            alert.showAndWait();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("REINDIRIZZAMENTO");
            alert.setContentText("STAI PER ESSERE REINDIRIZZATO ALLA HOME PAGE");
            alert.showAndWait();
            backHome();
            return;
        }

        backHome();
    }

    public void backHome() throws IOException {
        Stage stage = (Stage) disiscriviBTN.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(percorso));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //nothing
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }


    public void sendNotification() throws SQLException {
        List<Invito> notifications = inviteBean.getInvites(persona);

        for (Invito invito : notifications) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CLOSE);
            alert.setTitle("INVITO TORNEO");
            alert.setContentText(String.format("Sei stato invitato da: '%s' '%s' \n per il torneo '%s' del '%s' alle ore '%s' con costo '%s'. Accetti?",
                    invito.getSender().getNome(), invito.getSender().getCognome(), invito.getTorneo().getNome(), invito.getTorneo().getData(), invito.getTorneo().getOra(), invito.getTorneo().getPrezzo()));
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                inviteBean.acceptInvite(persona, invito.getTorneo());
                Alert newalert = new Alert(Alert.AlertType.INFORMATION);
                newalert.setTitle(successo);
                newalert.setContentText("Complimenti, ti sei iscritto con successo al torneo.");
               }
            else 
            {
            	if (alert.getResult() == ButtonType.NO) {
                inviteBean.removeInvite(invito.getId());
                Alert newalert = new Alert(Alert.AlertType.INFORMATION);
                newalert.setTitle(successo);
                newalert.setContentText("Hai rifiutato l'invito al torneo.");
                }
            }
        }
    }
    
}
