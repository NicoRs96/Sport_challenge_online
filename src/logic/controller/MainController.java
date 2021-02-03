package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.Iscrivitiview;

public class MainController implements Initializable{
	
	Stage stage;
	Parent root;	

	@FXML
	 private Button iscrivitiBTN;
	
	@FXML
	 private Button loginBTN;
	 
	@FXML
	private void iscriviti(ActionEvent event) throws Exception {
				
		Stage stageTheButtonBelongs = (Stage) iscrivitiBTN.getScene().getWindow();
	   	Iscrivitiview iscrizioneIscrivitiview = new Iscrivitiview();
		iscrizioneIscrivitiview.apriIscr(stageTheButtonBelongs);
	}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
	}	
	

}
