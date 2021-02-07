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

public class HomePageRenterController implements Initializable{
	
	@FXML
	private Button esciBTN;
	
	@FXML
	private void indietro(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) esciBTN.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing
	}

	
}
