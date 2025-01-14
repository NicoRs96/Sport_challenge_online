package logic.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.bean.LoginBean;
import logic.model.Persona;
import logic.view.HomePageRenterView;
import logic.view.Iscrivitiview;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.SortedMap;

public class MainController implements Initializable{
	
	private final LoginBean loginBean = new LoginBean();
	
	SortedMap<String, String> user;


	@FXML
	 private Button iscrivitiBTN;
	
	@FXML
	 private TextField emailTF;

	@FXML
	private PasswordField pwTF;

	@FXML
	private Button loginBtn;

	@FXML
	private void iscriviti(ActionEvent event) throws IOException  {
				
		Stage stageTheButtonBelongs = (Stage) iscrivitiBTN.getScene().getWindow();
	   	Iscrivitiview iscrizioneIscrivitiview = new Iscrivitiview();
		iscrizioneIscrivitiview.apriIscr(stageTheButtonBelongs);
	}

	@FXML
	private boolean login(ActionEvent event) throws SQLException, IOException {

		if (emailTF.getText().trim().isEmpty() || pwTF.getText().trim().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE INSERIMENTO DATI");
			alert.setContentText("Il sistema non accetta campi vuoti, per favore riprova.");
			alert.showAndWait();
			return false;
		}
		
		loginBean.setUsername(emailTF.getText().trim());
		loginBean.setPassword(pwTF.getText().trim());
		
		if (loginBean.userExist() == 1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE LOGIN");
			alert.setContentText("Non esiste nessun utente associato a questa combinazione email/password.");
			alert.showAndWait();
			return false;
		}
		else {
		    user = loginBean.getUser(); 
		}	
		
	
		int id = Integer.parseInt(user.get("ID"));
		String nome = user.get("NOME");
		String cognome = user.get("COGNOME");
		String email = user.get("EMAIL");
		String telefono = user.get("TELEFONO");
		LocalDate data = LocalDate.parse(user.get("DATADINASCITA"));
		String isRent = user.get("RENT");

		Persona persona = new Persona(id, nome, cognome, email, data, telefono, isRent);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("ACCESSO ESEGUITO");
		String name = String.format("Bentornato, %s", nome);
		alert.setContentText(name);
		alert.showAndWait();

		if (isRent.equals("1")) {
			Stage stage = (Stage) loginBtn.getScene().getWindow();
			HomePageRenterView homePageRenterView = new HomePageRenterView(persona);
            homePageRenterView.apriHPRenter(stage);
			return true;
		}

		//modificare view e mettere quella dello sportsman
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/HomepageSportman.fxml"));
		Parent root = loader.load();
		HomePageSportmanController homePageSportmanController = loader.getController();
		homePageSportmanController.setPersona(persona);
		homePageSportmanController.sendNotification();
		Scene scene = new Scene(root);
		stage.setScene(scene);
        return true;
		
	}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
	}	
	

}
