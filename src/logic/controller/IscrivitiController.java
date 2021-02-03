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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IscrivitiController implements Initializable{
	
	@FXML
	private Button indietroButton;
	
	@FXML
	private TextField NomeTF;
	@FXML
	private TextField CognomeTF;
	@FXML
	private DatePicker DataDP;
	@FXML
	private TextField TelefonoTF;
	@FXML
	private TextField emailTF;
	@FXML
	private TextField PassowrdTF;	
	
	
	
	@FXML
	private void indietro(ActionEvent event) throws IOException {
		
		Stage stage = (Stage)indietroButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
	}

}
