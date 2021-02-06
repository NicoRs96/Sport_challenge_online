package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageRenterView {
	public HomePageRenterView() {
		//nothing
	}
	
	public void apriHPRenter(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageRenter.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
