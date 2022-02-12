package logic.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.controller.CercaCampoSportivoController;
import logic.model.Persona;


import java.io.IOException;

public class CercaCampoSportivoView {
	private final Persona persona;
	public CercaCampoSportivoView(Persona persona) {
		this.persona = persona;
		//nothing
	}

	public void apriCercaCampoSportivo(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/CercaCampoSportivo.fxml"));
		Parent root = loader.load();
		CercaCampoSportivoController cercaCampoSportivoController = loader.getController();
		cercaCampoSportivoController.setPersona(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
