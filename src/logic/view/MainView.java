package logic.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
	
	
	
	@Override
    public void start(Stage stage) throws Exception {
     Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.setTitle("Sport Challenge Online");
     stage.show();
     
    }
	
		
	
}
