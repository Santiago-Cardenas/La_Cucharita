package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventoryModuleControllerGUI {
	
	//FXML Atributtes
	@FXML
	private TableView<?> tvInventory;

	@FXML
	private TableColumn<?, ?> tcIngredients;

	@FXML
	private TableColumn<?, ?> tcAmount;

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
	 
	public InventoryModuleControllerGUI()
	{
		inventoryModuleStage = new Stage();
	}
	  

	public void showInventoryModule() throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inventoryModule.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		inventoryModuleStage.setScene(scene);
		inventoryModuleStage.setTitle("Staff Module");
		inventoryModuleStage.show(); 
	}
	
	
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
