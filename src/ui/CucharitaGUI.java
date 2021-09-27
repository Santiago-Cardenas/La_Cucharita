package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
import model.Ingredient;
import model.User;
import model.UserManager;


public class CucharitaGUI {
   
    //Attributes
	public UserManager userManager;
	
    private StaffModuleControllerGUI staffModule;
    
    public InventoryModuleControllerGUI inventoryModule;
    
    public MenuModuleControllerGUI menuModule;
    
    public OrderModuleControllerGUI orderModule;
    
    public String userLoggedIn;

	private String USERS_DATA_CSV_PATH = "data/UsersDataExported.csv";
    
    private String INGREDIENTS_DATA_CSV_PATH = "data/IngredientsDataExported.csv";
    
	public String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	
	public String BILLBOARD_REPORT_FILE_NAME = "data/report.txt";
    
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
    	orderModule = new OrderModuleControllerGUI(this);
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
    	String userId = txtUserLogin.getText();
    	String password = pFLogin.getText();
		
    	if(userManager.accountLogIn(userId,password))
    	{
    		setUserLoggedIn(userId);
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
		loginStage.setTitle("MenuModule");
		loginStage.show(); 
		
		menuModule.initializeTableView();
		menuModule.initializeComboBox();
	}

	@FXML
	void openOrdersModule(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ordersModule.fxml"));
		fxmlLoader.setController(orderModule);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.setTitle("OrderModule");
		loginStage.show(); 
		
		orderModule.initializeTableView();
		orderModule.initializeComboBox();
		orderModule.initializeOrderCodesComboBox();

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
	
	public void importUsersData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader (USERS_DATA_CSV_PATH));
		String line = br.readLine();
		int count=0;
		while (line != null) {
			count++;
			String[] parts = line.split("\\|");
			if (count > 1) {
				User newUser = new User(parts[0], parts[1], parts[2], parts[3]);
				userManager.addNewUser(newUser);
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public void importIngredientsData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader (INGREDIENTS_DATA_CSV_PATH));
		String line = br.readLine();
		int count=0;
		while (line != null) {
			count++;
			String[] parts = line.split("\\|");
			if (count > 1) {
				double qt = Double.parseDouble(parts[1]);
				Ingredient newIngredient = new Ingredient(parts[0], qt, parts[2]);
				inventoryModule.inventoryManager.addIngredient(newIngredient);
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public void exportUsersData() throws IOException {
		FileWriter fw = new FileWriter(USERS_DATA_CSV_PATH,false);
		fw.write("ID|USERNAME|PASSWORD|BIRTHDAY\n");
		for (int i = 0; i < userManager.getUsers().size(); i++) {
			User myUser = userManager.getUsers().get(i);
			fw.write(myUser.getId() + "|" + myUser.getPassword() + "|" + myUser.getPassword() + "|" + myUser.getBirthDay() + "\n");
		}
		fw.close();
	}
	
	public void exportIngredientsData() throws IOException {
		FileWriter fw = new FileWriter(INGREDIENTS_DATA_CSV_PATH,false);
		fw.write("NAME|QT|UNITS\n");
		for (int i = 0; i < userManager.getUsers().size(); i++) {
			Ingredient myIngredient = inventoryModule.inventoryManager.getIngredients().get(i);
			fw.write(myIngredient.getIngredientName()+ "|" + myIngredient.getIngredientQT() + "|" + myIngredient.getIngredientUnits() + "\n");
		}
		fw.close();
	}
	
	public String getUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUserLoggedIn(String userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}
	/*	
	public String exportDangerousBillboardsReport() {
		String report="===========================\n"+
					  "DANGEROUS BILLBOARD REPORT\n"+
					  "===========================\n"+
					  "The dangerous billboard are:\n";
		int pos=1;
		for(int i=0; i<billboards.size();i++) {
			
			double area=billboards.get(i).calculateArea();
			if(area>=160) {
				report+= pos +". Billboard " + billboards.get(i).getBrand() + " with area " + area + "\n";
				pos++;
			}
		}
		return report;
	}
	*/
}
