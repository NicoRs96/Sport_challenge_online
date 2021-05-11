package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class HomePageRenterController implements Initializable{
	private Persona persona;
	private final CancellaAccountBean cancellaAccountBean = new CancellaAccountBean();
	
	@FXML
	private Button esciBTN;

	@FXML
	private Button inserisciCSBTN;

	@FXML
	private Button gesticiCSBTN;

	@FXML
	private Button creaTornBTN;

	@FXML
	private Button gestisciTornBTN;

	@FXML
	private Button disiscriviBTN;
	
	String percorso = "/view/Main.fxml";


	
	@FXML
	private void indietro(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) esciBTN.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(percorso));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
		
	}

	@FXML
	private void inserisci(ActionEvent event) throws IOException {
		Stage stage = (Stage) inserisciCSBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InserisciCampoRenter.fxml"));
		Parent root = loader.load();
		InserisciCampoRenterController inserisciCampoRenterController = loader.getController();
		inserisciCampoRenterController.setPersonas(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	@FXML
	private void gestisci(ActionEvent event) throws IOException {
		Stage stage = (Stage) gesticiCSBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GestisciCampiRenter.fxml"));
		Parent root = loader.load();
		GestisciCampiRenterController gestisciCampiRenterController = loader.getController();
		gestisciCampiRenterController.setPersona(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	@FXML
	private void creaTorneo(ActionEvent event) throws IOException, SQLException {
		Stage stage = (Stage) creaTornBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaTorneo.fxml"));
		Parent root = loader.load();
		CreaTorneoController creaTorneoController = loader.getController();
		creaTorneoController.setPersona(persona);
		creaTorneoController.getCampiByRenterId();
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	@FXML
	private void gestisciTorneo(ActionEvent event) throws IOException, SQLException {
		Stage stage = (Stage) gestisciTornBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GestisciTorneiRenter.fxml"));
		Parent root = loader.load();
		GestisciTorneiRenterController gestisciTorneiRenterController = loader.getController();
		gestisciTorneiRenterController.setPersona(persona);
		gestisciTorneiRenterController.getTorneiByRenterId();
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


    public void cancellaAccountRenter() throws SQLException, IOException {
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
				Parent root = loader.load();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				return;
			}
		
		Stage stage = (Stage) disiscriviBTN.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(percorso));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
    }
}
