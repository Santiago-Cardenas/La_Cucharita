package ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;
import model.UserManager;

public class CucharitaGUI {

	public CucharitaGUI() {
		UserManager userManager = new UserManager();
	}
	
	@FXML
    public void createAccount(ActionEvent event) throws IOException {
		if (verifyInfoInput()==1) {
    	//id de donde se saca el date			LocalDate bday = date_Birthday.getValue();
    	
    	// id de las txtBox User user = new User (txt_NewUsername.getText(),txt_NewPassword.getText(),txt_Id.getText(), bday.toString());
    	
    	//userManager.add(user);
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Account succesfully created");

		alert.showAndWait();
		}
    }
	
	public int verifyInfoInput() {
		int readyToCreateAccount=1;
		/*
		 id den txtBox if(txt_NewUsername.getText().equals("") && txt_NewPassword.getText().equals("") && txt_Id.getText().equals("") && bday.toString().equals("")) {
	
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
			readyToCreateAccount=-1;
		}
		*/
		return readyToCreateAccount;
	}
}
