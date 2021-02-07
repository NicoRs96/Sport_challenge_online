package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CercaCampoSportivoView {
	
	public CercaCampoSportivoView() {
		//nothing
	}

	public void apriCercaCampoSportivo(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/CercaCampoSportivo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
