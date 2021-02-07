package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.CercaCampoSportivoView;

public class HomePageSportmanController implements Initializable{
	
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
	private void indietro (ActionEvent event) throws IOException{
		
		Stage stage = (Stage) esciBTN.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	private void cercaCampo() throws IOException {
		Stage stage = (Stage) cercaCSBTN.getScene().getWindow();
		CercaCampoSportivoView cercaCampoSportivoView = new CercaCampoSportivoView();
		cercaCampoSportivoView.apriCercaCampoSportivo(stage);
	}
		
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing
	}

}
