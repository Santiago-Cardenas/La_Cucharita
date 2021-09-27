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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
	private ComboBox<String> cmbOrderCode;

	@FXML
	private TextField txtDishAmount;

	@FXML
	private TableView<Order> tvOrders;

	@FXML
	private TableColumn<Order, UUID> tcOrderInfo;

	@FXML
	private TableColumn<Order, OrderState> tcOrderStatus;


	//Attributes
	private Stage orderModuleStage;

	private ObservableList<Menu> observableMenuList;

	private ObservableList<Order> observableOrdersList;

	private ObservableList<String> observableMenuListToPreview;
	
	private ObservableList<String> observableOrderListToPreview;

	private ArrayList<Menu> newOrderToPreview;

	private OrderManager orderManager;

	private CucharitaGUI cucharitaGUI;

	private boolean orderReadyToCreate=false;

	public OrderModuleControllerGUI(CucharitaGUI cucharitaGUI) {
		setOrderModuleStage(new Stage());
		orderModulePane = new Pane();
		orderManager = new OrderManager();
		newOrderToPreview= new ArrayList<>();
		this.cucharitaGUI = cucharitaGUI;
	}

	public void initializeTableView() {
		orderManager.insertionSortCode();
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
	
	public void initializeOrderCodesComboBox(){
		observableOrderListToPreview = FXCollections.<String>observableArrayList();
		String orderCodeList="";
		String[] parts = null;
		int orderArrayListSize=orderManager.getOrder().size();
		for(int i=0; i<orderArrayListSize;i++) {
			orderCodeList += orderManager.getOrder().get(i).getCode()+"++";
		}

		if(orderArrayListSize>0) {
			parts = orderCodeList.split("\\+\\+");
		}

		for(int i=0; i<orderArrayListSize;i++) {
			observableOrderListToPreview.add(parts[i]);
		}
		cmbOrderCode.setValue("Choose an option");
		cmbOrderCode.setItems(observableOrderListToPreview);
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
						System.out.println(cucharitaGUI.menuModule.menuManager.getMenu().get(i));
						System.out.println(cucharitaGUI.menuModule.menuManager.getMenu().get(i).getMenuName());
						if(cucharitaGUI.menuModule.menuManager.getMenu().get(i).getMenuName().equals(dishName)){
							System.out.println("Entro al for de crear orden");
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
		boolean okToCreate=checkIfServingIsPosible();
		if(orderReadyToCreate==true) {
			LocalDate facturationDay = facturationDate.getValue();

			orderManager.createOrder(newOrderToPreview,facturationDay.toString(),okToCreate);
			initializeTableView();
			newOrderToPreview.clear();
			initializeOrderPreviewTableView();
			initializeOrderCodesComboBox();
			

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

	public boolean checkIfServingIsPosible() {
		boolean canServe=true;
		String msg="";
		double currentIngredientsQT=0;
		double requestedIngredientsQT=0;
		double ingredientsLeft=0;
		int menuRequestedQT=0;
		for (int i =0; i<newOrderToPreview.size();i++) {
			System.out.println("Entro al for de menu");
			System.out.println(newOrderToPreview.get(i).getIngredientsUsed().size());
			menuRequestedQT=newOrderToPreview.get(i).getMenuQTRequested();
			msg+="For the " + newOrderToPreview.get(i).getMenuName() + " dish\n";
			for(int p=0; p<cucharitaGUI.inventoryModule.inventoryManager.getIngredients().size();p++) {
				System.out.println("Entro al for de array ingrediente\n"+ "ingrediente del array de ingredientes");
				System.out.println(cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(p).getIngredientName());
				System.out.println(cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(p).getIngredientQT());
				for(int j=0;j<newOrderToPreview.get(i).getIngredientsUsed().size();j++) {
					System.out.println("Entro al for de cada ingrediente\n"+ "ingrediente del array de ingredientes del menu");
					System.out.println(newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientName());
					System.out.println(newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientQT());
					if(newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientName().equals(cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(p).getIngredientName())) {
						System.out.println("Entro al if de ingrediente");
						System.out.println(newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientName());
						System.out.println(newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientQT());
						currentIngredientsQT=cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(j).getIngredientQT();
						requestedIngredientsQT= menuRequestedQT * newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientQT();
						ingredientsLeft=currentIngredientsQT-requestedIngredientsQT;
						if(ingredientsLeft<0) {
							System.out.println("Entro al if de no hay suficientes ingredientes");
							System.out.println(ingredientsLeft);
							msg+="There is not enough: \n" + newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientName() + "\n";
							canServe=false;
						}
					}
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
			decreaseIngredientQT();
		}
		return canServe;
	}

	public void decreaseIngredientQT() {
		double requestedIngredientsQT=0;
		double currentIngredientsQT=0;
		double ingredientsLeft=0;
		int menuRequestedQT=0;
		@SuppressWarnings("unused")
		String ingredientName="";
		@SuppressWarnings("unused")
		String ingredientUnits="";
		for (int i =0; i<newOrderToPreview.size();i++) {
			menuRequestedQT=newOrderToPreview.get(i).getMenuQTRequested();
			for(int p=0; p<cucharitaGUI.inventoryModule.inventoryManager.getIngredients().size();p++) {
				for(int j=0;j<newOrderToPreview.get(i).getIngredientsUsed().size();j++) {
					if(newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientName().equals(cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(p).getIngredientName())) {
						currentIngredientsQT=cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(j).getIngredientQT();
						requestedIngredientsQT= menuRequestedQT * newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientQT();
						ingredientsLeft=currentIngredientsQT-requestedIngredientsQT;
						ingredientUnits=newOrderToPreview.get(i).getIngredientsUsed().get(j).getIngredientUnits();
						cucharitaGUI.inventoryModule.inventoryManager.getIngredients().get(p).setIngredientQT(ingredientsLeft);
					}
				}
			}
		}
	}

	@FXML
	void changeStatusToDelivered(ActionEvent event) {
		double precioDePedido=0;
		if(cmbOrderCode.getValue().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select an order");

			alert.showAndWait();
		}
		else {
			String code=cmbOrderCode.getValue();
			for(int i=0;i<orderManager.getOrder().size();i++) {
				if(orderManager.getOrder().get(i).getCode().equals(code)){
					for(int j=0;j<orderManager.getOrder().get(i).getMenusRequested().size();j++) {
						precioDePedido+=orderManager.getOrder().get(i).getMenusRequested().get(j).getMenuPrice();
					}
					orderManager.getOrder().get(i).setOrderState(OrderState.DELIVERED);
					int userLoggedInPos = cucharitaGUI.userManager.findUser(cucharitaGUI.getUserLoggedIn());
					int cantidadDePedidosEntregados =cucharitaGUI.userManager.getUsers().get(userLoggedInPos).getPedidosEntregados();
					double cantidadDeDineroRecaudado =cucharitaGUI.userManager.getUsers().get(userLoggedInPos).getDineroTotalDeCombosVendidos();
					cucharitaGUI.userManager.getUsers().get(userLoggedInPos).setPedidosEntregados(cantidadDePedidosEntregados+1);
					cucharitaGUI.userManager.getUsers().get(userLoggedInPos).setDineroTotalDeCombosVendidos(cantidadDeDineroRecaudado+precioDePedido);
					
				}
			}
			initializeTableView();
			tvOrders.refresh();
		}
	}

	@FXML
	void changeStatusToInProcess(ActionEvent event) {
		if(cmbOrderCode.getValue().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select an order");

			alert.showAndWait();
		}
		else {
			String code=cmbOrderCode.getValue();
			for(int i=0;i<orderManager.getOrder().size();i++) {
				if(orderManager.getOrder().get(i).getCode().equals(code)){
					orderManager.getOrder().get(i).setOrderState(OrderState.ON_GOING);
				}
			}
			initializeTableView();
			tvOrders.refresh();
		}
	}

	@FXML
	void changeStatusToPending(ActionEvent event) {
		if(cmbOrderCode.getValue().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select an order");

			alert.showAndWait();
		}
		else {
			String code=cmbOrderCode.getValue();
			for(int i=0;i<orderManager.getOrder().size();i++) {
				if(orderManager.getOrder().get(i).getCode().equals(code)){
					orderManager.getOrder().get(i).setOrderState(OrderState.PENDING);
				}
			}
			initializeTableView();
			tvOrders.refresh();
		}
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

