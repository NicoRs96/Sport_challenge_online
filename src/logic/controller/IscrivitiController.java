package controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import view.HomePageRenterView;
import view.HomePageSportmanView;

import javax.swing.*;

import bean.IscrivitiBean;
import dao.IscrizioneDao;

public class IscrivitiController implements Initializable {

	private IscrivitiBean IscrivitiBean = new IscrivitiBean();
	
	
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
		
		IscrivitiBean.setNome(nomeTF.getText().trim());
		IscrivitiBean.setCognome(cognomeTF.getText().trim());
		IscrivitiBean.setData(dataDP.getValue());
		IscrivitiBean.setMail(mailTF.getText().trim());
		IscrivitiBean.setPw(pWTF.getText().trim());
		IscrivitiBean.setTelefono(telefonoTF.getText().trim());
		IscrivitiBean.setCb(rentCB.isSelected());

		//controllo età
		if (IscrivitiBean.checkData() == 1)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(avviso);
			alert.setContentText("La piattaforma � riservata ad utenti di et� > 14, per favore riprova.");
			alert.showAndWait();
			return;
		}

		//controllo email
		if(IscrivitiBean.checkMail()==1){
		{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle(avviso);
				alert.setContentText("L'email inserita non è nel formato corretto, per favore riprova.");
				alert.showAndWait();
				return;
			}
		}

		//check utente già registrato
        boolean isRent = IscrivitiBean.getCb();
        if(IscrivitiBean.checkUtente()==1)
        {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(avviso);
			alert.setContentText("C� gi�  un utente registrato con questo nome e cognome, per favore riprova.");
			alert.showAndWait();
		}

        //aggiunta utente nel database
		if (IscrivitiBean.aggiungiUtente()== 1) 
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("INSERIMENTO AVVENUTO CON SUCCESSO");
			alert.setContentText("Complimenti, ti sei registrato con successo");
			alert.showAndWait();
			
			if(isRent){
                Stage stage = (Stage) indietroButton.getScene().getWindow();
                HomePageRenterView homePageRenterView = new HomePageRenterView();
                homePageRenterView.apriHPRenter(stage);
			}
			//modificare view e mettere quella dello sportsman
			else{
                Stage stage = (Stage) indietroButton.getScene().getWindow();
                HomePageSportmanView homePageSportmanView = new HomePageSportmanView();
                homePageSportmanView.apriHPSportman(stage);
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
