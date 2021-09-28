package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

    private CucharitaGUI cucharitagui;
    
    @FXML
    private Pane mainPane = new Pane();
  
    
    public Main()
    {
    	cucharitagui = new CucharitaGUI();
    	try {
			cucharitagui.importUsersData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	try {
			cucharitagui.importIngredientsData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			cucharitagui.importMenuData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*
    	try {
			cucharitagui.importOrderData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
    
	public static void main(String [] args)
	{
		launch(args);
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// TODO Auto-generated method stub
		/**
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		cucharitagui = new CucharitaGUI();
		fxmlLoader.setController(cucharitagui);
 		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("La Cucharita");
		//primaryStage.show();
		 
		*/
		
		cucharitagui.showLogin(mainPane);
		//classroomgui.loadPane(mainPane);
	}			
	
}
