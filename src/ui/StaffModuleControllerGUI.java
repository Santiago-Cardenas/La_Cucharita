package ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
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
    private PasswordField pFOldPassword;
    
    @FXML
    private TextField txtIdChange;
    
    @FXML
    private Pane registerPane;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserId;

    @FXML
    private PasswordField pFUserPassword;

    @FXML
    private DatePicker dPUserBirthday;

    @FXML
    private TableView<User> tvEmployeesList;

    @FXML
    private TableColumn<User, String> userNameTC;

    @FXML
    private TableColumn<User, String> idTC;

    @FXML
    private TableColumn<User, String> birthdayTC;

    //Attributes
	private Stage staffModuleStage; 
	
	private ObservableList<User> observableList;
	
	private CucharitaGUI cucharitaGUI;

	
	public StaffModuleControllerGUI(CucharitaGUI cucharitaGUI)
	{
		setStaffModuleStage(new Stage());
		staffModulePane = new Pane();
		this.cucharitaGUI = cucharitaGUI;
	}
	
	
    private void initializeTableView() 
    {
		observableList = FXCollections.observableArrayList(cucharitaGUI.userManager.getUsers());
		
		tvEmployeesList.setItems(observableList);
		userNameTC.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
		idTC.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
		birthdayTC.setCellValueFactory(new PropertyValueFactory<User,String>("birthDay"));
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
    	String oldPassword = pFOldPassword.getText();		
    	String newPassword = pFNewPassword.getText();		

    	cucharitaGUI.userManager.changePassword(id, oldPassword,newPassword);

    }
    
    
    @FXML
    public void goToRegistEmployee(ActionEvent event) throws IOException 
    {
     	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
    	fxmlLoader.setController(this);
    	Parent register = fxmlLoader.load();
    	staffModulePane.getChildren().setAll( register);
    }

	public int verifyInfoInput() 
	{
		int readyToCreateAccount=1;
		
		if(txtUserName.getText().equals("") && pFUserPassword.getText().equals("") && txtUserId.getText().equals("") && dPUserBirthday.toString().equals("")) 
		{
	
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
			readyToCreateAccount=-1;
		}
		
		return readyToCreateAccount;
	}

    
    @FXML
    public void registNewUser(ActionEvent event) throws IOException 
    {
    	if (verifyInfoInput() == 1) 
    	{
        	LocalDate bday = dPUserBirthday.getValue();
        	String username = txtUserName.getText();
        	String password = pFUserPassword.getText();
        	String id = txtUserId.getText();
        	
        	User newUser = new User(id,username, password, bday.toString());
        	newUser.setPedidosEntregados(0);
        	newUser.setDineroTotalDeCombosVendidos(0);
        	cucharitaGUI.userManager.addNewUser(newUser);
        	cucharitaGUI.exportUsersData();
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("Employee succesfully registered");

    		alert.showAndWait();
    		    		
    	}
    	
    	
    }
    
    @FXML
    public void showEmployeeList(ActionEvent event) throws IOException 
    {
     	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listOfEmployees.fxml"));
    	fxmlLoader.setController(this);
    	Parent employeesList = fxmlLoader.load();
    	staffModulePane.getChildren().setAll( employeesList );
    	cucharitaGUI.userManager.sortById();
    	initializeTableView();
    }
	
    @FXML
    private void openModules(ActionEvent event)
    {}
    
    @FXML
    public void goBackToModules(ActionEvent event) throws IOException    {   	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskManager.fxml"));
		fxmlLoader.setController(cucharitaGUI);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		cucharitaGUI.getLoginStage().setScene(scene);
		cucharitaGUI.getLoginStage().setTitle("task Manager");
		cucharitaGUI.getLoginStage().show(); 
    }
    
    @FXML
    public void goBackToMStaffModule(ActionEvent event) throws IOException {   	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("staffModule.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		cucharitaGUI.getLoginStage().setScene(scene);
		cucharitaGUI.getLoginStage().setTitle("task Manager");
		cucharitaGUI.getLoginStage().show(); 	
    }


	public Stage getStaffModuleStage() {
		return staffModuleStage;
	}


	public void setStaffModuleStage(Stage staffModuleStage) {
		this.staffModuleStage = staffModuleStage;
	}


}
