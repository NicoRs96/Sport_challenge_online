package view;

import java.io.IOException;

import controller.HomePageRenterController;
import controller.HomePageSportmanController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Persona;

public class HomePageRenterView {
	private Persona persona;
	public HomePageRenterView(Persona persona) {
		this.persona = persona;
		//nothing
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void apriHPRenter(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePageRenter.fxml"));
		Parent root = (Parent) loader.load();
		HomePageRenterController homePageRenterController = loader.getController();
		homePageRenterController.setPersona(persona);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
}
