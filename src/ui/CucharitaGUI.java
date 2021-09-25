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
    
    public InventoryModuleControllerGUI inventoryModule;
    
    public MenuModuleControllerGUI menuModule;
    
    public UserManager userManager;
    
    //private InventoryManager inventoryManager; 
    
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
    	inventoryModule = new InventoryModuleControllerGUI(this);
    	menuModule = new MenuModuleControllerGUI(this);
    	userManager = new UserManager();
    	//inventoryManager = new InventoryManager();
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
    	String userId = txtUserLogin.getText();
    	String password = pFLogin.getText();
    	
    	if(userManager.accountLogIn(userId,password))
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
	private void openStaffModule(ActionEvent event) throws IOException {
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
		
		inventoryModule.initializeTableView();
		inventoryModule.initializeComboBox();
	}

	@FXML
	void openMenuModule(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuModule.fxml"));
		fxmlLoader.setController(menuModule);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.setTitle("InventoryModule");
		loginStage.show(); 
		
		menuModule.initializeTableView();
		menuModule.initializeComboBox();
	}

	@FXML
	void openOrdersModule(ActionEvent event) 
	{

	}

	@FXML
    void goToLogIn(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.setTitle("Log in");
		loginStage.show(); 
		
    }
	//getter
	public Stage getLoginStage()
	{
		return loginStage;
	}
	
	/*
	public InventoryManager getInventoryManager()
	{
		return inventoryManager;
	}
	 */
}
