package ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import model.UserManager;

public class CucharitaGUI {

	
	private UserManager userManager;
	
	private Stage loginStage;
	//@FXML Attributes:
	
    @FXML
    private PasswordField pFLogin;

    @FXML
    private Button btnLogIn;

    @FXML
    private TextField txtIdLogin;

    @FXML
    private Pane loginPane;
	
    
    public CucharitaGUI() 
	{
		UserManager userManager = new UserManager();
		loginPane = new Pane();
		loginStage = new Stage();
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
	
	
    @FXML
    public void openModules(ActionEvent event) 
    {
    	String id = txtIdLogin.getText();
    	String password = pFLogin.getText();
    	
    	if(userManager.accountLogIn( id, password))
    	{
    		
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



	public void showLogin( Pane mainPane ) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.setTitle("Log in");
		loginStage.show(); 

	}
		
	

}
