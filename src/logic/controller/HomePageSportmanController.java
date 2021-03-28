package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import bean.CancellaAccountBean;
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
import model.Persona;
import view.CercaCampoSportivoView;

public class HomePageSportmanController implements Initializable{
	private Persona persona;
	private CancellaAccountBean cancellaAccountBean = new CancellaAccountBean();
	
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
	
	String percorso = "/view/Main.fxml";
	
	
	@FXML
	private void indietro (ActionEvent event) throws IOException{
		
		Stage stage = (Stage) esciBTN.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(percorso));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	private void cercaCampo() throws IOException {
		Stage stage = (Stage) cercaCSBTN.getScene().getWindow();
		CercaCampoSportivoView cercaCampoSportivoView = new CercaCampoSportivoView(persona);
		cercaCampoSportivoView.apriCercaCampoSportivo(stage);
	}


	public void cercaTornei() throws IOException {
		Stage stage = (Stage) cercaTornBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CercaTorneo.fxml"));
		Parent root = (Parent) loader.load();
		CercaTorneoController cercaTorneoController = loader.getController();
		cercaTorneoController.setPersona(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void gestisciEventi() throws IOException, SQLException, ParseException {
		Stage stage = (Stage) iMieiEventiBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GestisciMieiEventi.fxml"));
		Parent root = (Parent) loader.load();
		GestisciMieiEventiController gestisciMieiEventiController = loader.getController();
		gestisciMieiEventiController.setPersona(persona);
		gestisciMieiEventiController.getCampi();
		gestisciMieiEventiController.getTornei();
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void gestisciLivello() throws IOException, SQLException, ParseException {
		Stage stage = (Stage) specificalvlBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ScegliLivello.fxml"));
		Parent root = (Parent) loader.load();
		ScegliLivelloController scegliLivelloController = loader.getController();
		scegliLivelloController.setPersona(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void gestisciSportPreferito() throws IOException, SQLException, ParseException {
		Stage stage = (Stage) sportPrefeBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SportPreferito.fxml"));
		Parent root = (Parent) loader.load();
		SportPreferitoController sportPreferitoController = loader.getController();
		sportPreferitoController.setPersona(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void cancellaAccount() throws SQLException, IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("ATTENZIONE");
		alert.setContentText("ATTENZIONE, OPERAZIONE IRREVERSIBILE.\nPREMERE OK per CONFERMARE");
		alert.showAndWait();
		if((alert.getResult() == ButtonType.OK)&&
			(cancellaAccountBean.deleteAccount(persona.getId()))){
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("SUCCESS");
				alert.setContentText("ACCOUNT CANCELLATO CON SUCCESSO");
				alert.showAndWait();
				Stage stage = (Stage) disiscriviBTN.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource(percorso));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				return;
			}
		
		Stage stage = (Stage) disiscriviBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(percorso));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing
	}

	public void setPersona(Persona persona){
		this.persona = persona;
	}


}
