package logic.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MeteoIncompleteException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public MeteoIncompleteException () {
		//constructor
		
	}
	
	public void showMessage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMAZIONE");
		alert.setHeaderText("Problema col meteo\n");
		alert.setContentText("Non � stato possibile recuperare il meteo previsto per il giorno del torneo\nAbbiamo comunque trovato le temperature\n");
		alert.showAndWait();
		
	}
}
	
	

