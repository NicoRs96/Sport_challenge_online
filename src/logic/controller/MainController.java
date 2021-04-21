package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

import bean.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Persona;
import view.HomePageRenterView;
import view.HomePageSportmanView;
import view.Iscrivitiview;

public class MainController implements Initializable{
	
	private LoginBean loginBean = new LoginBean();
	
	SortedMap<String, String> user = new TreeMap<>();


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
	private void login(ActionEvent event) throws SQLException, IOException {

		if (emailTF.getText().trim().isEmpty() || pwTF.getText().trim().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE INSERIMENTO DATI");
			alert.setContentText("Il sistema non accetta campi vuoti, per favore riprova.");
			alert.showAndWait();
			return;
		}
		
		loginBean.setUsername(emailTF.getText().trim());
		loginBean.setPassword(pwTF.getText().trim());
		
		if (loginBean.userExist() == 1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRORE LOGIN");
			alert.setContentText("Non esiste nessun utente associato a questa combinazione email/password.");
			alert.showAndWait();
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
			return;
		}

		//modificare view e mettere quella dello sportsman
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		HomePageSportmanView homePageSportmanView = new HomePageSportmanView(persona);
        homePageSportmanView.apriHPSportman(stage);
        
		
	}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
	}	
	

}
