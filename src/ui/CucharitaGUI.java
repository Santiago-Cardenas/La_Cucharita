package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ingredient;
import model.Menu;
import model.Order;
import model.OrderState;
import model.User;
import model.UserManager;


public class CucharitaGUI {

	//Attributes
	public UserManager userManager;

	private StaffModuleControllerGUI staffModule;

	public InventoryModuleControllerGUI inventoryModule;

	public MenuModuleControllerGUI menuModule;

	public OrderModuleControllerGUI orderModule;
	
	public ReportsModuleControllerGUI reportModule;

	public String userLoggedIn;

	private String USERS_DATA_CSV_PATH = "data/UsersDataExported.csv";

	private String INGREDIENTS_DATA_CSV_PATH = "data/IngredientsDataExported.csv";
	
	private String MENU_DATA_CSV_PATH = "data/MenuDataExported.csv";
	
	private String ORDERS_DATA_CSV_PATH = "data/OrdersDataExported.csv";

	public String REPORT_A_FILE_NAME = "data/reportA.txt";
	
	public String REPORT_B_FILE_NAME = "data/reportB.txt";

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
		reportModule= new ReportsModuleControllerGUI(this);
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

	@FXML
	void openReportsModule(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reports.fxml"));
		fxmlLoader.setController(reportModule);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		loginStage.setScene(scene);
		loginStage.setTitle("ReportsModule");
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
		ArrayList<LocalDate> facturationDates= new ArrayList<LocalDate>();
		while (line != null) {
			count++;
			String[] parts = line.split("\\|");
			if (count > 1) {
				User newUser = new User(parts[0], parts[1], parts[2], parts[3]);
				newUser.setPedidosEntregados(Integer.parseInt(parts[4]));
				newUser.setDineroTotalDeCombosVendidos(Double.parseDouble(parts[5]));
				if(parts.length>5) {
					for(int i=0;i<parts.length;i++) {
						if(i>5) {
							 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							LocalDate date = LocalDate.parse(parts[i], formatter);
							facturationDates.add(date);
						}
					}
					userManager.addNewUser(newUser);
					newUser.setOrderDates(facturationDates);
				}
				else {
					userManager.addNewUser(newUser);
				}
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
	
	public void importMenuData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader (MENU_DATA_CSV_PATH));
		String line = br.readLine();
		String ingredientName="";
		int count=0;
		double ingredientqt=0;
		ArrayList<Ingredient> newIngredients= new ArrayList<Ingredient>();
		while (line != null) {
			count++;
			String[] parts = line.split("\\|");
			if (count > 1) {
				for (int i = 0; i < parts.length; i++) {
					if(i>1 && i+1<parts.length) {
						ingredientName=parts[i];
						ingredientqt=Double.parseDouble(parts[i+1]);
						Ingredient newIngredient = new Ingredient(ingredientName,ingredientqt);
						newIngredients.add(newIngredient);
					}
				}
				Menu newMenu = new Menu(parts[0],Double.parseDouble(parts[1]),newIngredients);
				menuModule.menuManager.addToMenu(newMenu);	
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public void importOrderData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader (ORDERS_DATA_CSV_PATH));
		String line = br.readLine();
		int count=0;
		int menupos=0;
		ArrayList<Menu> newMenu= new ArrayList<Menu>();
		while (line != null) {
			count++;
			String[] parts = line.split("\\|");
			if (count > 1) {
				for (int i = 0; i < parts.length;i++) {
					if(i>2) {
						if(menuModule.menuManager.findMenu(parts[3])!=-1) {
							menupos=menuModule.menuManager.findMenu(parts[3]);
							menuModule.menuManager.getMenu().get(menupos).setMenuQTRequested(Integer.parseInt(parts[4]));
							newMenu.add(menuModule.menuManager.getMenu().get(menupos));	
						}
					}
				}
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(parts[3], formatter);
				if(parts[1].equalsIgnoreCase("PENDING")) {
					Order newOrder = new Order(newMenu,OrderState.PENDING,date);
					newOrder.setCode(parts[0]);
					orderModule.orderManager.addToOrders(newOrder);
				}
				else if(parts[1].equalsIgnoreCase("ON_GOING")) {
					Order newOrder = new Order(newMenu,OrderState.ON_GOING,date);
					newOrder.setCode(parts[0]);
					orderModule.orderManager.addToOrders(newOrder);
				}
				else if(parts[1].equalsIgnoreCase("DELIVERED")) {
					Order newOrder = new Order(newMenu,OrderState.DELIVERED,date);
					newOrder.setCode(parts[0]);
					orderModule.orderManager.addToOrders(newOrder);
				}
			}
			line = br.readLine();
		}
		br.close();
	}

	public void exportUsersData() throws IOException {
		FileWriter fw = new FileWriter(USERS_DATA_CSV_PATH,false);
		fw.write("ID|USERNAME|PASSWORD|BIRTHDAY|ORDERS_TAKEN|MONEY_COLLECTED|ORDER_DATES\n");
		for (int i = 0; i < userManager.getUsers().size(); i++) {
			User myUser = userManager.getUsers().get(i);
			fw.write(myUser.getId() + "|" + myUser.getUsername() + "|" + myUser.getPassword() + "|" + myUser.getBirthDay() +  "|" + myUser.getPedidosEntregados() + "|" + myUser.getDineroTotalDeCombosVendidos());
			for(int j=0;j<userManager.getUsers().get(i).getOrderDates().size();j++) {
				fw.write("|" + myUser.getOrderDates().get(j));
			}
			fw.write("\n");
		}
		fw.close();
	}

	public void exportIngredientsData() throws IOException {
		FileWriter fw = new FileWriter(INGREDIENTS_DATA_CSV_PATH,false);
		fw.write("NAME|QT|UNITS\n");
		for (int i = 0; i < inventoryModule.inventoryManager.getIngredients().size(); i++) {
			Ingredient myIngredient = inventoryModule.inventoryManager.getIngredients().get(i);
			fw.write(myIngredient.getIngredientName()+ "|" + myIngredient.getIngredientQT() + "|" + myIngredient.getIngredientUnits() + "\n");
		}
		fw.close();
	}
	
	public void exportMenusData() throws IOException {
		FileWriter fw = new FileWriter(MENU_DATA_CSV_PATH,false);
		fw.write("NAME|PRICE|INGREDIENTS\n");
		for (int i = 0; i < menuModule.menuManager.getMenu().size(); i++) {
			Menu myMenu = menuModule.menuManager.getMenu().get(i);
			fw.write(myMenu.getMenuName()+ "|" + myMenu.getMenuPrice() );
			for (int j = 0; j < menuModule.menuManager.getMenu().get(i).getIngredientsUsed().size(); j++) {
				fw.write("|" + myMenu.getIngredientsUsed().get(j).getIngredientName() + "|" + myMenu.getIngredientsUsed().get(j).getIngredientQT() );
			}
			fw.write("\n");
		}
		fw.close();
	}
	
	public void exportOrdersData() throws IOException {
		FileWriter fw = new FileWriter(ORDERS_DATA_CSV_PATH,false);
		fw.write("ORDER_CODE|ORDER_STATE|ORDER_DATE|MENU_NAME|MENU_QT\n");
		for (int i = 0; i < orderModule.orderManager.getOrder().size();i++) {
			Order myOrder = orderModule.orderManager.getOrder().get(i);
			fw.write(myOrder.getCode()+ "|" + myOrder.getOrderState()+ "|" + myOrder.getOrderDate() );
			for(int p=0;p<orderModule.orderManager.getOrder().get(i).getMenusRequested().size();p++) {
				Menu myMenu = orderModule.orderManager.getOrder().get(i).getMenusRequested().get(p);
				fw.write("|" + myMenu.getMenuName()+ "|" + myMenu.getMenuQTRequested());
			}
			fw.write("\n");
		}
		fw.close();
	}
	

	public String getUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUserLoggedIn(String userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}

	public String exportReportA(LocalDate initialDay,LocalDate finalDay) {
		int userLoggedInPos =userManager.findUser(getUserLoggedIn());
		String report="===========================\n"+
				"		REPORT A\n"+
				"===========================\n";
		LocalDate currentFacturationDate=null;
		for(int i=0;i<userManager.getUsers().get(userLoggedInPos).getOrderDates().size();i++){
			currentFacturationDate=userManager.getUsers().get(userLoggedInPos).getOrderDates().get(i);
			if(initialDay.compareTo(currentFacturationDate) * currentFacturationDate.compareTo(finalDay) >= 0) {
				int pos=1;
				for(int j=0; j<userManager.getUsers().size();j++) {
					if(userManager.getUsers().get(j).getPedidosEntregados()>0) {
						report+= pos +". ID " + userManager.getUsers().get(j).getId() + " has delivered a total of " +  userManager.getUsers().get(j).getPedidosEntregados() + " orders and collected a total of " + userManager.getUsers().get(j).getDineroTotalDeCombosVendidos() + "$\n";
						pos++;
					}
				}
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Please select valid dates");

				alert.showAndWait();
			}
		}
		return report;
	}

	public void generateReportA(LocalDate initialDay,LocalDate finalDay) throws IOException{
		File file = new File (REPORT_A_FILE_NAME);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(exportReportA(initialDay,finalDay).getBytes());
		fos.close();
	}
	
	public String exportReportB(LocalDate initialDay,LocalDate finalDay) {
		int userLoggedInPos =userManager.findUser(getUserLoggedIn());
		String report="===========================\n"+
				"		REPORT B\n"+
				"===========================\n";
		LocalDate currentFacturationDate=null;
		for(int i=0;i<userManager.getUsers().get(userLoggedInPos).getOrderDates().size();i++){
			currentFacturationDate=userManager.getUsers().get(userLoggedInPos).getOrderDates().get(i);
			if(initialDay.compareTo(currentFacturationDate) * currentFacturationDate.compareTo(finalDay) >= 0) {
						int pos=1;
						for(int j=0; j<menuModule.menuManager.getMenu().size();j++) {
							report+= pos +". Dish Name: " + menuModule.menuManager.getMenu().get(j).getMenuName() + " has been requested a total of " +  menuModule.menuManager.getMenu().get(j).getTotalQTRequested() + " times and the total of money colleted from this dish is: " + menuModule.menuManager.getMenu().get(j).getTotalMoneyPaid() + "$\n";
							pos++;

						}
			}
		}
		return report;
	}

	public void generateReportB(LocalDate initialDay,LocalDate finalDay) throws IOException{
		File file = new File (REPORT_B_FILE_NAME);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(exportReportB(initialDay,finalDay).getBytes());
		fos.close();
	}

}
