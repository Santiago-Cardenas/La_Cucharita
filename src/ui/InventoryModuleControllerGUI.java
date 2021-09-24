package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ingredient;
import model.InventoryManager;
import model.User;

public class InventoryModuleControllerGUI {
	
	//FXML Attributes
	
	@FXML
    private Pane inventoryModulePane;
	
	@FXML
	private TableView<Ingredient> tvInventory;

	@FXML
	private TableColumn<Ingredient, String> tcIngredients;

	@FXML
	private TableColumn<Ingredient, Double> tcAmount;
	
    @FXML
    private TableColumn<Ingredient, String> tcUnits;

	@FXML
	private TextField txtIngredientNameAdd;

	@FXML
	private TextField txtUnits;

	@FXML
	private TextField txtIngredientAmountAdd;

	@FXML
	private TextField txtIngredientNameEdit;

	@FXML
	private TextField txtIngredientAmountEdit;
	  
	//Attributes
	private Stage inventoryModuleStage;
	 
	private ObservableList<Ingredient> observableInventoryList;
	
	private InventoryManager inventoryManager;
	
	private CucharitaGUI cucharitaGUI;

		
		public InventoryModuleControllerGUI(CucharitaGUI cucharitaGUI){
			setInventoryModuleStage(new Stage());
			inventoryModulePane = new Pane();
			inventoryManager= new InventoryManager();
			this.cucharitaGUI = cucharitaGUI;
		}
		
		
	    public void initializeTableView() {
			observableInventoryList = FXCollections.observableArrayList(inventoryManager.getIngredients());
			
			tvInventory.setItems(observableInventoryList);
			tcIngredients.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("Name"));
			tcAmount.setCellValueFactory(new PropertyValueFactory<Ingredient,Double>("Amount"));
			tcUnits.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("Units"));
		}
	
	    public Stage getInventoryModuleStage() {
			return inventoryModuleStage;
		}


		public void setInventoryModuleStage(Stage inventoryModuleStage) {
			this.inventoryModuleStage = inventoryModuleStage;
		}
	
	//FXML methods:

	@FXML
    public void addIngredient(ActionEvent event) {
		String ingredientName= txtIngredientNameAdd.getText();
		String ingredientUnits= txtUnits.getText();
		double ingredientQT= Double.parseDouble(txtIngredientAmountAdd.getText());
		initializeTableView();
		if(ingredientName.equalsIgnoreCase(null) && ingredientUnits.equalsIgnoreCase(null) && ingredientQT<0) {
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
		}
		else {
			Ingredient newIngredient= new Ingredient(ingredientName,ingredientQT,ingredientUnits);
			inventoryManager.addIngredient(newIngredient);
			initializeTableView();
		}
    }

    @FXML
    public void decreaseIngredientAmount(ActionEvent event) {

    }

    @FXML
    public void deleteIngredient(ActionEvent event) {

    }

    @FXML
    public void increaseIngredientAmount(ActionEvent event) {

    }
}
