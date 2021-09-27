package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ingredient;
import model.Menu;
import model.MenuManager;

public class MenuModuleControllerGUI {


	@FXML
	private Pane menuModulePane;

	@FXML
	private TableView<Menu> tvMenu;

	@FXML
	private TableColumn<Menu, String> tcDishName;

	@FXML
	private TableColumn<Menu, Double> tcDishPrice;

	@FXML
	private TableView<Ingredient> tvIngredientsPreview;

	@FXML
	private TableColumn<Ingredient, String> tcIngredientsName;

	@FXML
	private TableColumn<Ingredient, Double> tcIngredientAmount;


	@FXML
	private Label lblDishesInfo;

	@FXML
	private Label lblDishNamePreview;

	@FXML
	private Label lblDishPricePreview;

	@FXML
	private TextField txtIngredientAmount;

	@FXML
	private TextField txtNewDishName;

	@FXML
	private SplitMenuButton smbIngredient;

	@FXML
	private ComboBox<String> cmbIngredient;

	@FXML
	private TextField txtNewDishPrice;


	//Attributes
	private Stage menuModuleStage;

	private ObservableList<Menu> observableMenuList;

	private ObservableList<String> observableIngredientsList;

	private ObservableList<Ingredient> observableIngredientsListToPreview;
	
	ArrayList<Ingredient> newIngredientsToPreview;
	
	public MenuManager menuManager;

	private CucharitaGUI cucharitaGUI;
	
	private boolean dishNameReadyToCreate=false;
	
	private boolean dishIngredientsReadyToCreate=false;

	public MenuModuleControllerGUI(CucharitaGUI cucharitaGUI){

		setMenuModuleStage(new Stage());
		menuModulePane = new Pane();
		menuManager = new MenuManager();
		newIngredientsToPreview = new ArrayList<Ingredient>();
		this.cucharitaGUI = cucharitaGUI;		
	}

	public void initializeTableView() {
		observableMenuList = FXCollections.observableArrayList(menuManager.getMenu());

		tvMenu.setItems(observableMenuList);
		tcDishName.setCellValueFactory(new PropertyValueFactory<Menu,String>("menuName"));
		tcDishPrice.setCellValueFactory(new PropertyValueFactory<Menu,Double>("menuPrice"));
	}
	
	public void initializeIngredientsTableView() {
		observableIngredientsListToPreview = FXCollections.observableArrayList(newIngredientsToPreview);

		tvIngredientsPreview.setItems(observableIngredientsListToPreview);
		tcIngredientsName.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("ingredientName"));
		tcIngredientAmount.setCellValueFactory(new PropertyValueFactory<Ingredient,Double>("ingredientQT"));
	}

	public void initializeComboBox(){
		observableIngredientsList = FXCollections.<String>observableArrayList();
		String ingredientsNameList="";
		String[] parts = null;
		int ingredientsArrayListSize=cucharitaGUI.inventoryModule.inventoryManager.ingredients.size();
		for(int i=0; i<ingredientsArrayListSize;i++) {
			ingredientsNameList += cucharitaGUI.inventoryModule.inventoryManager.ingredients.get(i).getIngredientName()+"++";
		}

		if(ingredientsArrayListSize>0) {
			parts = ingredientsNameList.split("\\+\\+");
		}

		for(int i=0; i<ingredientsArrayListSize;i++) {
			observableIngredientsList.add(parts[i]);
		}
		cmbIngredient.setValue("Choose an option");
		cmbIngredient.setItems(observableIngredientsList);
	}

	@FXML
	void StartDishPreview(ActionEvent event) {
		String dishName= txtNewDishName.getText();
		
		if(dishName.equals("") && txtNewDishPrice.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please write a name and price for the menu");

			alert.showAndWait();
		}
		else if(txtNewDishPrice.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please write a price for the menu");

			alert.showAndWait();
		}else {
			double dishPrice= Double.parseDouble(txtNewDishPrice.getText());
			String dishPricelbl= dishPrice + "$";
			if(verifyInput(dishName, dishPrice)==true) {
				lblDishNamePreview.setText(dishName);
				lblDishPricePreview.setText(dishPricelbl);
				dishNameReadyToCreate=true;
			}
		}
	}
	
	public boolean verifyInput(String dishName,double dishPrice) {
		boolean allinput=true;
		if(dishName.equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please write a name for the dish");

			alert.showAndWait();
			allinput=false;
		}
		else if(dishPrice<=0) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please write a valid menu price");

			alert.showAndWait();
			allinput=false;
		}

		return allinput;
	}

	@FXML
	public void addIngredientToNewDish(ActionEvent event) {
		String ingredientName= cmbIngredient.getValue();
		if(ingredientName.equals("Choose an option")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select an ingredient");

			alert.showAndWait();
		}
		else if(txtIngredientAmount.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select a valid ingredient QT");

			alert.showAndWait();
		}
		else {
			double ingredientQT= Double.parseDouble(txtIngredientAmount.getText());

			if(ingredientQT<=0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Please select a valid ingredient QT");

				alert.showAndWait();
			}
			else {
				if(checkIfIngredientAlreadyExists(ingredientName, ingredientQT)==false) {
					Ingredient newIngredient = new Ingredient(ingredientName,ingredientQT);
					newIngredientsToPreview.add(newIngredient);
					initializeIngredientsTableView();
					dishIngredientsReadyToCreate=true;
					}			
				else {
					initializeIngredientsTableView();
					tvIngredientsPreview.refresh();
					dishIngredientsReadyToCreate=true;
				}
				cmbIngredient.setValue("Choose an option");
				txtIngredientAmount.clear();
			}
		}
	}

	@FXML
	public void createNewDishToMenu(ActionEvent event) throws IOException {
		if(dishIngredientsReadyToCreate==true && dishNameReadyToCreate==true) {
			String dishName= txtNewDishName.getText();
			double dishPrice= Double.parseDouble(txtNewDishPrice.getText());

			menuManager.createMenu(dishName,dishPrice,newIngredientsToPreview);
			initializeTableView();
			cucharitaGUI.exportMenusData();
			newIngredientsToPreview.clear();
			initializeIngredientsTableView();


			lblDishNamePreview.setText("");
			lblDishPricePreview.setText("");
			txtNewDishName.setText("");
			txtNewDishPrice.setText("");
			dishIngredientsReadyToCreate=false;
			dishNameReadyToCreate=false;
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
		}

	}


	public boolean checkIfIngredientAlreadyExists(String ingredientName,Double ingredientQT) {
		boolean found=false;
		double qt=ingredientQT;
		for(int i=0;i<newIngredientsToPreview.size() && !found;i++) {
			if(ingredientName.equalsIgnoreCase(newIngredientsToPreview.get(i).getIngredientName())) {
				qt=Double.parseDouble(txtIngredientAmount.getText());
				newIngredientsToPreview.get(i).setIngredientQT(qt);
				found=true;
			}
		}
		return found;
	}

	public Stage getMenuModuleStage() {
		return menuModuleStage;
	}

	public void setMenuModuleStage(Stage menuModuleStage) {
		this.menuModuleStage = menuModuleStage;
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
