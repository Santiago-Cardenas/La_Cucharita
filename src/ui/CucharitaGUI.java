package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


public class CucharitaGUI {
   
    //Attributes
    private StaffModuleControllerGUI staffModule;
    private InventoryModuleControllerGUI inventoryModule;
    
    @FXML
    private Pane taskManagerPane;
    public CucharitaGUI() 
    {
    	staffModule = new StaffModuleControllerGUI();   
    	inventoryModule = new InventoryModuleControllerGUI();
	}
	     
	@FXML
	private void openStaffModule(ActionEvent event) throws IOException 
	{
		staffModule.showStaffModule();
	}
	
	
	@FXML
	private void openInventoryModule(ActionEvent event) throws IOException 
	{
		inventoryModule.showInventoryModule();
	}

	@FXML
	void openMenuModule(ActionEvent event) 
	{

	}

	@FXML
	void openOrdersModule(ActionEvent event) 
	{

	}

	
	

}
