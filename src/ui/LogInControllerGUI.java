package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UserManager;

public class LogInControllerGUI {

	private UserManager logInUserManager;
	private Stage loginStage;
	private CucharitaGUI cucharitaguiLogIn;
	//@FXML Attributes:
	
    @FXML
    private PasswordField pFLogin;

    @FXML
    private Button btnLogIn;

    @FXML
    private TextField txtUserLogin;

    @FXML
    private Pane loginPane;
    
    public LogInControllerGUI()
    {
		loginPane = new Pane();
		loginStage = new Stage();
		cucharitaguiLogIn = new CucharitaGUI();
		logInUserManager = new UserManager();
    	
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
	
    @FXML
    private void openModules(ActionEvent event) throws IOException 
    {
    	String user = txtUserLogin.getText();
    	String password = pFLogin.getText();
    	
    	if(logInUserManager.accountLogIn(user,password))
    	{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskManager.fxml"));
    		fxmlLoader.setController(cucharitaguiLogIn);
    		Parent root = fxmlLoader.load();
    		Scene scene = new Scene(root);
    		loginStage.setScene(scene);
    		loginStage.setTitle("Task Manager");
    		loginStage.show(); 
    	}
    	
    }
    
	
}
