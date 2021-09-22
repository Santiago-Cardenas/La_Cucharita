package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Ingredient;
import model.User;

public class InventoryModuleControllerGUI {
	
	//FXML Attributes
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
	private ComboBox<?> cmbxUnits;

	@FXML
	private TextField txtIngredientAmountAdd;

	@FXML
	private TextField txtIngredientNameEdit;

	@FXML
	private TextField txtIngredientAmountEdit;
	  
	//Attributes
	private Stage inventoryModuleStage;
	 
	private ObservableList<Ingredient> observableList2;
	
	
	
	public InventoryModuleControllerGUI()
	{
		inventoryModuleStage = new Stage();
	}
	  
	
	/*
    private void initializeTableView() 
    {
		observableList2 = FXCollections.observableArrayList(staffUserManager.getUsers());
		
		tvInventory.setItems(observableList2);
		tcIngredients.setCellValueFactory(new PropertyValueFactory<User,String>("ingredientName"));
		tcAmount.setCellValueFactory(new PropertyValueFactory<User,String>("ingredientQT"));
		tcUnits.setCellValueFactory(new PropertyValueFactory<User,String>("birthDay"));
	}

	*/
	
	//FXML methods:
	
	
	
    @FXML
    public void addIngredient(ActionEvent event) {

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
