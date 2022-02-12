package logic.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.controller.HomePageSportmanController;
import logic.model.Persona;


import java.io.IOException;

public class HomePageSportmanView {
	private Persona persona;

	public HomePageSportmanView(Persona persona) {
		this.persona = persona;
		//nothing
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void apriHPSportman(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("logic/view/HomePageSportman.fxml"));
		Parent root = loader.load();
		HomePageSportmanController homePageSportmanController = loader.getController();
		homePageSportmanController.setPersona(persona);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
