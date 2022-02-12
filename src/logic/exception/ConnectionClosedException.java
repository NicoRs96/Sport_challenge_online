package logic.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnectionClosedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConnectionClosedException (String message)
	{		

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ERRORE");
		alert.setHeaderText("Errore durante l'esecuzione");
		alert.setContentText(message);
		alert.showAndWait();
		
	}
}
