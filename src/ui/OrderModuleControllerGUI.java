package ui;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ingredient;
import model.Menu;
import model.Order;
import model.OrderManager;
import model.OrderState;

public class OrderModuleControllerGUI {

	@FXML
	private Pane orderModulePane;
	
	@FXML
    private DatePicker facturationDate;

	@FXML
	private TableView<Menu> tvMenuOrder;

	@FXML
	private TableColumn<Menu, String> tcDishNameOrder;

	@FXML
	private TableColumn<Menu, Integer> tcDishQTOrder;

	@FXML
	private ComboBox<String> cmbDishName;

	@FXML
	private TextField txtDishAmount;

	@FXML
	private TableView<Order> tvOrders;

	@FXML
	private TableColumn<Order, UUID> tcOrderInfo;

	@FXML
	private TableColumn<Order, OrderState> tcOrderStatus;

	@FXML
	private Label lblOrderInfo;

	//Attributes
	private Stage orderModuleStage;

	private ObservableList<Menu> observableMenuList;
	
	private ObservableList<Order> observableOrdersList;

	private ObservableList<String> observableMenuListToPreview;
	
	private ArrayList<Menu> newOrderToPreview;

	private OrderManager orderManager;

	private CucharitaGUI cucharitaGUI;
	
	private InventoryModuleControllerGUI inventoryModule;
	
	private boolean orderReadyToCreate=false;

	public OrderModuleControllerGUI(CucharitaGUI cucharitaGUI) {
		setOrderModuleStage(new Stage());
		orderModulePane = new Pane();
		orderManager = new OrderManager();
		newOrderToPreview= new ArrayList<>();
		this.cucharitaGUI = cucharitaGUI;
	}
	
	public void initializeTableView() {
		observableOrdersList = FXCollections.observableArrayList(orderManager.getOrder());

		tvOrders.setItems(observableOrdersList);
		tcOrderInfo.setCellValueFactory(new PropertyValueFactory<Order,UUID>("code"));
		tcOrderStatus.setCellValueFactory(new PropertyValueFactory<Order,OrderState>("orderState"));
	}
	
	public void initializeOrderPreviewTableView() {
		observableMenuList = FXCollections.observableArrayList(newOrderToPreview);

		tvMenuOrder.setItems(observableMenuList);
		tcDishNameOrder.setCellValueFactory(new PropertyValueFactory<Menu,String>("menuName"));
		tcDishQTOrder.setCellValueFactory(new PropertyValueFactory<Menu,Integer>("menuQTRequested"));
	}
	
	public void initializeComboBox(){
		observableMenuListToPreview = FXCollections.<String>observableArrayList();
		String menuNameList="";
		String[] parts = null;
		int menuArrayListSize=cucharitaGUI.menuModule.menuManager.menu.size();
		for(int i=0; i<menuArrayListSize;i++) {
			menuNameList += cucharitaGUI.menuModule.menuManager.menu.get(i).getMenuName()+"++";
		}

		if(menuArrayListSize>0) {
			parts = menuNameList.split("\\+\\+");
		}

		for(int i=0; i<menuArrayListSize;i++) {
			observableMenuListToPreview.add(parts[i]);
		}
		cmbDishName.setValue("Choose an option");
		cmbDishName.setItems(observableMenuListToPreview);
	}

	@FXML
	void addDishToOrder(ActionEvent event) {
		String dishName= cmbDishName.getValue();
		if(cmbDishName.getValue().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select a dish");

			alert.showAndWait();
		}
		else {
			if(verifyInput(dishName, txtDishAmount.getText())==true) {
				int dishQT= Integer.parseInt(txtDishAmount.getText());
				if(checkIfDishAlreadyExists(dishName,dishQT)==false) {
					for(int i=0;i<cucharitaGUI.menuModule.menuManager.getMenu().size();i++) {
						if(cucharitaGUI.menuModule.menuManager.getMenu().get(i).getMenuName().equals(dishName)){
							cucharitaGUI.menuModule.menuManager.getMenu().get(i).setMenuQTRequested(dishQT);
							newOrderToPreview.add(cucharitaGUI.menuModule.menuManager.getMenu().get(i));
						}
					}
					initializeOrderPreviewTableView();
					orderReadyToCreate=true;
				}
				else {
					initializeOrderPreviewTableView();
					tvMenuOrder.refresh();
					orderReadyToCreate=true;
				}
				cmbDishName.setValue("Choose an option");
				txtDishAmount.clear();
			}
		}

	}
	
	public boolean verifyInput(String dishName,String dishQT) { 
		boolean allinput=true;
		if(dishName.equals("Choose an option")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select a dish");

			alert.showAndWait();
			allinput=false;
		}
		else if(dishQT.equals("") || Integer.parseInt(dishQT)<=0) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please write a valid menu QT");

			alert.showAndWait();
			allinput=false;
		}
		return allinput;
	}
	
	public boolean checkIfDishAlreadyExists(String dishName,int dishQT) { 
		boolean found=false;
		int qt=dishQT;
		for(int i=0;i<newOrderToPreview.size() && !found;i++) {
			if(dishName.equalsIgnoreCase(newOrderToPreview.get(i).getMenuName())) {
				qt=Integer.parseInt(txtDishAmount.getText());
				newOrderToPreview.get(i).setMenuQTRequested(qt);;
				found=true;
			}
		}
		return found;
	}

	@FXML
	void addOrderToList(ActionEvent event) {
		boolean okToCreate=checkIfServingIsPosible(newOrderToPreview);
		if(orderReadyToCreate==true) {
			LocalDate facturationDay = facturationDate.getValue();
			
			orderManager.createOrder(newOrderToPreview,facturationDay.toString(),okToCreate);
			initializeTableView();
			newOrderToPreview.clear();
			initializeOrderPreviewTableView();

			cmbDishName.setValue("Choose an option");
			txtDishAmount.clear();
			orderReadyToCreate=false;
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
		}
	}
	
	public boolean checkIfServingIsPosible(ArrayList<Menu> menusRequested) {
		boolean canServe=true;
		String msg="";
		double currentIngredientsQT=0;
		double requestedIngredientsQT=0;
		double ingredientsLeft=0;
		int menuRequestedQT=0;
		System.out.println("CHECKIFSERVINGPOSIBLE\n-------------------------------------\n-------------------------------------\n");
		for (int i =0; i<menusRequested.size();i++) {
			System.out.println("Entro al for de menu");
			menuRequestedQT=menusRequested.get(i).getMenuQTRequested();
			System.out.println(menuRequestedQT + " menuQT");
			msg+="For the " + menusRequested.get(i).getMenuName() + " dish\n";
			for(int j=0;j<menusRequested.get(i).getIngredientsUsed().size();j++) {
				System.out.println("Entro al for de ingredientes");
				currentIngredientsQT=cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(j).getIngredientQT();
				requestedIngredientsQT= menuRequestedQT * menusRequested.get(i).getIngredientsUsed().get(j).getIngredientQT();
				ingredientsLeft=currentIngredientsQT-requestedIngredientsQT;
				System.out.println(currentIngredientsQT + "los que hay");
				System.out.println(requestedIngredientsQT + "pedidos");
				System.out.println(ingredientsLeft + "sobrantes");
				if(ingredientsLeft<0) {
					System.out.println("Entro al if de cuando no hay suficientes ingredientes");
					msg+="There is not enough: \n" + menusRequested.get(i).getIngredientsUsed().get(j).getIngredientName() + "\n";
					canServe=false;
				}
			}
			msg+="To make the order";
		}
		if(canServe==false){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText(msg);

			alert.showAndWait();
		}
		else {
			decreaseIngredientQT(menusRequested);
		}
		return canServe;
	}
	
	public void decreaseIngredientQT(ArrayList<Menu> menusRequested) {
		double requestedIngredientsQT=0;
		double currentIngredientsQT=0;
		double ingredientsLeft=0;
		int menuRequestedQT=0;
		String ingredientName="";
		String ingredientUnits="";
		System.out.println("DECREASEINGREDIENTQT\n-------------------------------------\n-------------------------------------\n");
		for (int i =0; i<menusRequested.size();i++) {
			menuRequestedQT=menusRequested.get(i).getMenuQTRequested();
			for(int p=0; p<cucharitaGUI.inventoryModule.inventoryManager.getIngredients().size();p++) {
				for(int j=0;j<menusRequested.get(i).getIngredientsUsed().size();j++) {
					if(menusRequested.get(i).getIngredientsUsed().get(j).getIngredientName().equals(cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(p).getIngredientName())) {
						currentIngredientsQT=cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(j).getIngredientQT();
						requestedIngredientsQT= menuRequestedQT * menusRequested.get(i).getIngredientsUsed().get(j).getIngredientQT();
						ingredientsLeft=currentIngredientsQT-requestedIngredientsQT;
						ingredientUnits=menusRequested.get(i).getIngredientsUsed().get(j).getIngredientUnits();
						cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(i).setIngredientQT(ingredientsLeft);
					}
				}
			}
		}
	}
	@FXML
	void changeStatusToDelivered(ActionEvent event) {

	}

	@FXML
	void changeStatusToInProcess(ActionEvent event) {

	}

	@FXML
	void changeStatusToPending(ActionEvent event) {

	}

	public Stage getOrderModuleStage() {
		return orderModuleStage;
	}


	public void setOrderModuleStage(Stage orderModuleStage) {
		this.orderModuleStage = orderModuleStage;
	}

	@FXML
	public void goBackToModules(ActionEvent event) throws IOException 
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("taskManager.fxml"));
		fxmlLoader.setController(cucharitaGUI);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		cucharitaGUI.getLoginStage().setScene(scene);
		cucharitaGUI.getLoginStage().setTitle("task Manager");
		cucharitaGUI.getLoginStage().show(); 
	}



}

