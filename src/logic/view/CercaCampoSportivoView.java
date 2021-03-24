package view;

import java.io.IOException;

import controller.CercaCampoSportivoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Persona;

public class CercaCampoSportivoView {
	private Persona persona;
	public CercaCampoSportivoView(Persona persona) {
		this.persona = persona;
		//nothing
	}

	public void apriCercaCampoSportivo(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CercaCampoSportivo.fxml"));
		Parent root = (Parent) loader.load();
		CercaCampoSportivoController cercaCampoSportivoController = loader.getController();
		cercaCampoSportivoController.setPersona(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
