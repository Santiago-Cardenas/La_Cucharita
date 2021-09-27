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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ingredient;
import model.InventoryManager;

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
	
    @FXML
    private ComboBox<String> cmbUnits;
	  
	//Attributes
	private Stage inventoryModuleStage;
	 
	private ObservableList<Ingredient> observableInventoryList;
		
	private ObservableList<String> observableUnitsList;
	
	public InventoryManager inventoryManager;
	
	private CucharitaGUI cucharitaGUI;
	
		
		public InventoryModuleControllerGUI(CucharitaGUI cucharitaGUI){
			
			setInventoryModuleStage(new Stage());
			inventoryModulePane = new Pane();
			inventoryManager = new InventoryManager();
			this.cucharitaGUI = cucharitaGUI;
			
		}
		
		

	    	

	    public void initializeTableView()  
	    {
	    	inventoryManager.sortByQuantity();
			observableInventoryList = FXCollections.observableArrayList(inventoryManager.getIngredients());
			
			tvInventory.setItems(observableInventoryList);
			tcIngredients.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("ingredientName"));
			tcAmount.setCellValueFactory(new PropertyValueFactory<Ingredient,Double>("ingredientQT"));
			tcUnits.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("ingredientUnits"));
			
			
		}
	
	  
	    public void initializeComboBox()
	    {

	    	observableUnitsList = FXCollections.observableArrayList("Kg (Kilograms)","g (grams)","ml (mililiters)", "Units");
			cmbUnits.setValue("Choose an option");
			cmbUnits.setItems(observableUnitsList);
	    }
	    
	    


		public Stage getInventoryModuleStage() {
			return inventoryModuleStage;
		}


		public void setInventoryModuleStage(Stage inventoryModuleStage) {
			this.inventoryModuleStage = inventoryModuleStage;
		}
	
	//FXML methods:

	
	
	@FXML
    public void addIngredient(ActionEvent event) throws IOException {

		String ingredientName= txtIngredientNameAdd.getText();
		String ingredientUnits= cmbUnits.getValue();
		double ingredientQT= Double.parseDouble( txtIngredientAmountAdd.getText() );
		
		initializeTableView();
		
		if(ingredientName.equalsIgnoreCase(null) && ingredientUnits.equalsIgnoreCase(null) && ingredientQT<0) 
		{
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
		}
		
		else 
		{
			Ingredient newIngredient= new Ingredient(ingredientName,ingredientQT,ingredientUnits);
			inventoryManager.addIngredient(newIngredient);
			initializeTableView();
			cucharitaGUI.exportIngredientsData();
		}
		clearFields();
    }

    @FXML
    public void decreaseIngredientAmount(ActionEvent event) 
    {
    	String ingredientName = txtIngredientNameEdit.getText();
    	String amount = txtIngredientAmountEdit.getText();
    	
    	if( ingredientName.toString().length() > 0 && amount.toString().length() > 0 )
    	{
        	inventoryManager.decreaseIngredient(ingredientName, amount);
         	initializeTableView();
         	tvInventory.refresh();
    	}
    	else
    	{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
    	}
    	clearFields();
    }

    @FXML
    public void increaseIngredientAmount(ActionEvent event) 
    {
		String ingredientName = txtIngredientNameEdit.getText();
		String amount =  txtIngredientAmountEdit.getText();
		
    	if( ingredientName.toString().length() > 0 && amount.toString().length() > 0)
    	{
    		inventoryManager.increaseIngredient(ingredientName, amount);
    		initializeTableView();
    		tvInventory.refresh();
    	}
    	else
    	{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please fill in all the information asked");

			alert.showAndWait();
    	}
    	clearFields();
    }
    
    @FXML
    public void deleteIngredient(ActionEvent event) 
    {
    	String ingredientName = txtIngredientNameEdit.getText();
    	
    	inventoryManager.deleteIngredient(ingredientName);
    	
    	initializeTableView();
    	clearFields();

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
		
		initializeTableView();
		
    }
    
    public void clearFields()
    {
    	txtIngredientNameAdd.clear();
    	txtIngredientAmountAdd.clear();
    	txtIngredientAmountEdit.clear();
    	txtIngredientNameEdit.clear();
    	cmbUnits.getSelectionModel().clearSelection();
    }



	public TableView<Ingredient> getTvInventory() {
		return tvInventory;
	}





	public void setTvInventory(TableView<Ingredient> tvInventory) {
		this.tvInventory = tvInventory;
	}
    
    
    

 
}
