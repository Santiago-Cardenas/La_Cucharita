package ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ReportsModuleControllerGUI {
	 @FXML
	    private DatePicker dpInitialDate;

	    @FXML
	    private DatePicker dpFinalDate;
	//Attributes
		private Stage reportModuleStage;

		private CucharitaGUI cucharitaGUI;

		public ReportsModuleControllerGUI(CucharitaGUI cucharitaGUI) {
			setReportModuleStage(new Stage());
			this.cucharitaGUI = cucharitaGUI;
		}
		
		
		

	    public Stage getReportModuleStage() {
			return reportModuleStage;
		}




		public void setReportModuleStage(Stage reportModuleStage) {
			this.reportModuleStage = reportModuleStage;
		}




		@FXML
	    void generateReportA(ActionEvent event) throws IOException {
			LocalDate initialDay = dpInitialDate.getValue();
			LocalDate finalDay = dpFinalDate.getValue();
			if(initialDay==null  ||   finalDay==null    ) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Please select both dates");

				alert.showAndWait();
			}else {
				cucharitaGUI.generateReportA(initialDay,finalDay);
			}
	    }

	    @FXML
	    void generateReportB(ActionEvent event) throws IOException {
	    	LocalDate initialDay = dpInitialDate.getValue();
			LocalDate finalDay = dpFinalDate.getValue();
			if(initialDay==null  ||   finalDay==null    ) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Please select both dates");

				alert.showAndWait();
			}else {
				cucharitaGUI.generateReportB(initialDay,finalDay);
			}
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
