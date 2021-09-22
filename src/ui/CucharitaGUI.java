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


public class CucharitaGUI {
   
    //Attributes
	
    private StaffModuleControllerGUI staffModule;
    
    private InventoryModuleControllerGUI inventoryModule;
    
    private UserManager userManager;
    
  	private Stage loginStage;
     
	//@FXML Attributes:
	
    @FXML
    private PasswordField pFLogin;

    @FXML
    private Button btnLogIn;

    @FXML
    private TextField txtUserLogin;

    @FXML
    private Pane loginPane;
    
    @FXML
    private Pane taskManagerPane;
    
    
    public CucharitaGUI() 
    {
    	staffModule = new StaffModuleControllerGUI(this);   
    	inventoryModule = new InventoryModuleControllerGUI();
    	userManager = new UserManager();
    	loginStage = new Stage();
    	
	}
	     
	public  void showLogin( Pane mainPane ) throws IOException
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
    	
    	if(userManager.accountLogIn(user,password))
    	{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskManager.fxml"));
    		fxmlLoader.setController(this);
    		Parent root = fxmlLoader.load();
    		Scene scene = new Scene(root);
    		loginStage.setScene(scene);
    		loginStage.setTitle("Task Manager");
    		loginStage.show(); 
    	}
    	
    }
    
	@FXML
	private void openStaffModule(ActionEvent event) throws IOException 
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("staffModule.fxml"));
		fxmlLoader.setController(staffModule);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.setTitle("Staff Module");
		loginStage.show(); 
		
	}
	
	
	@FXML
	private void openInventoryModule(ActionEvent event) throws IOException 
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inventoryModule.fxml"));
		fxmlLoader.setController(inventoryModule);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.setTitle("InventoryModule");
		loginStage.show(); 
	}

	@FXML
	void openMenuModule(ActionEvent event) 
	{

	}

	@FXML
	void openOrdersModule(ActionEvent event) 
	{

	}

	/*
	public void goToLogIn() throws IOException 
	{



	}
	*/
	//getter
	public Stage getLoginStage()
	{
		return loginStage;
	}
	
	

}
