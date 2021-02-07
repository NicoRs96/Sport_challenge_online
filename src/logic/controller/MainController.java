package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TreeMap;

import bean.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Persona;
import view.HomePageRenterView;
import view.HomePageSportmanView;
import view.Iscrivitiview;

public class MainController implements Initializable{
	
	private LoginBean loginBean = new LoginBean();
	
	TreeMap<String, String> user;


	@FXML
	 private Button iscrivitiBTN;
	
	@FXML
	 private TextField emailTF;

	@FXML
	private TextField pwTF;

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
		
	

		String nome = user.get("NOME");		
		String isRent = user.get("RENT");

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("ACCESSO ESEGUITO");
		String name = String.format("Bentornato, %s", nome);
		alert.setContentText(name);
		alert.showAndWait();

		if (isRent.equals("1")) {
			Stage stage = (Stage) loginBtn.getScene().getWindow();
			HomePageRenterView homePageRenterView = new HomePageRenterView();
            homePageRenterView.apriHPRenter(stage);
			return;
		}

		//modificare view e mettere quella dello sportsman
        Persona persona = new Persona(loginBean.getUsername());
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		//HomePageSportmanView homePageSportmanView = new HomePageSportmanView(persona);
        //homePageSportmanView.apriHPSportman(stage);
        
		
	}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
	}	
	

}
