package logic.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnectionClosedFXException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConnectionClosedFXException (String message)
	{		

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ERRORE");
		alert.setHeaderText("Errore durante l'esecuzione\n");
		alert.setContentText(message);
		alert.showAndWait();
		
	}
}
