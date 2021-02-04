package controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.IscrizioneBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class IscrivitiController implements Initializable {
	public static IscrizioneBean iscrizioneBean = new IscrizioneBean();

    @FXML
    private Button indietroButton;

    @FXML
    private TextField nomeTF;
    @FXML
    private TextField cognomeTF;
    @FXML
    private DatePicker dataDP;
    @FXML
    private TextField telefonoTF;
    @FXML
    private TextField mailTF;
    @FXML
    private TextField pWTF;
    @FXML
    private CheckBox rentCB;
    
    String avviso ="ERRORE DATI UTENTE";


    @FXML
    private void indietro(ActionEvent event) throws IOException {

        Stage stage = (Stage) indietroButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void confermaRegistrazione(ActionEvent event) throws SQLException, IOException {

		if (nomeTF.getText().trim().isEmpty() ||
                cognomeTF.getText().trim().isEmpty() || dataDP== null ||
                dataDP.getValue().toString().trim().isEmpty() ||
				mailTF.getText().trim().isEmpty() ||
				pWTF.getText().trim().isEmpty() ||
				telefonoTF.getText().trim().isEmpty()) {
            emptyFieldError();
            return;
        }

		//controllo età
		if((LocalDateTime.now().getYear() - dataDP.getValue().getYear()) < 14)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(avviso);
			alert.setContentText("La piattaforma è riservata ad utenti di età > 14, per favore riprova.");
			alert.showAndWait();
			return;
		}

		//controllo email
		if(!mailTF.getText().isEmpty())
		{
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(mailTF.getText().trim());
			if(!matcher.find()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle(avviso);
				alert.setContentText("L'email inserita non è nel formato corretto, per favore riprova.");
				alert.showAndWait();
				return;
			}
		}

		//check utente già registrato
        int isRent = rentCB.isSelected() ? 1 : 0;
        if(iscrizioneBean.checkIfUserAlreadyExists(nomeTF.getText().trim().toUpperCase(), cognomeTF.getText().trim().toUpperCase())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(avviso);
			alert.setContentText("C'è già un utente registrato con questo nome e cognome, per favore riprova.");
			alert.showAndWait();
		}

        //aggiunta utente nel database
		else if(iscrizioneBean.addUser(nomeTF.getText().trim().toUpperCase(), cognomeTF.getText().trim().toUpperCase(),
				mailTF.getText().trim(), dataDP.getValue().toString(), pWTF.getText().trim(),telefonoTF.getText().trim(), isRent)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("INSERIMENTO AVVENUTO CON SUCCESSO");
			alert.setContentText("Complimenti, ti sei registrato con successo");
			alert.showAndWait();
			if(isRent == 1){
                Stage stage = (Stage) indietroButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageRenter.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
			}
			//modificare view e mettere quella dello sportsman
			else{
                Stage stage = (Stage) indietroButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/view/HomepageRenter.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

		}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //nothing to do
    }


    public void emptyFieldError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE INSERIMENTO DATI");
        alert.setContentText("Il sistema non accetta campi vuoti, per favore riprova.");
        alert.showAndWait();
    }
}
