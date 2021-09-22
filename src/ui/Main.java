package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

    private CucharitaGUI cucharitagui;
    
    @FXML
    private Pane mainPane = new Pane();
  
    
    public Main()
    {
    	cucharitagui = new CucharitaGUI();
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
