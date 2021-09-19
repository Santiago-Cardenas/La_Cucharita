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

public class StaffModuleControllerGUI {

	//FXML Attributes
	
    @FXML
    private Pane staffModulePane;

    @FXML
    private Button btnRegistEmployee;

    @FXML
    private Button btnEmployeesList;

    @FXML
    private Button btnChangePassword;

    @FXML
    private Pane setPasswordPane;

    @FXML
    private PasswordField pFNewPassword;
    
    @FXML
    private TextField txtIdChange;
    
    //Attributes
	private Stage staffModuleStage; 
	
	private UserManager staffUserManager;
	
	
	
	public StaffModuleControllerGUI()
	{
		staffModuleStage = new Stage();
		staffModulePane = new Pane();
		staffUserManager = new UserManager();
	}
	
	
	
	
	public void showStaffModule() throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("staffModule.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		staffModuleStage.setScene(scene);
		staffModuleStage.setTitle("Staff Module");
		staffModuleStage.show(); 
	}
	
	

    @FXML
    public void goToSetPassword(ActionEvent event) throws IOException 
    {
    		
     	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("changePassword.fxml"));
    	fxmlLoader.setController(this);
    	Parent changePassword = fxmlLoader.load();
    	staffModulePane.getChildren().setAll( changePassword );
   
    }

    @FXML
    public void changePassowrdBTN(ActionEvent event) 
    {
   	 	String id = txtIdChange.getText();
   	 	String newPassword = pFNewPassword.getPromptText();		
   	 
   	 	staffUserManager.changePassword(id, newPassword);
    }
    
    
    @FXML
    public void goToRegistEmployee(ActionEvent event) 
    {

    }

    @FXML
    public void showEmployeeList(ActionEvent event) {

    }
	


}
