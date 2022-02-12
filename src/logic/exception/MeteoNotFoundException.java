package logic.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class MeteoNotFoundException extends IOException {
	
	
	private static final long serialVersionUID = 1L;

	public MeteoNotFoundException() {
		//costruttore
		
		
	}
	public void showMessage() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("AVVISO");
		alert.setHeaderText("Problema col meteo\n");
		alert.setContentText("Non � stato possibile recuperare il meteo\nControllare la connessione ad internet\n");
		alert.showAndWait();
	}
	
	
}
