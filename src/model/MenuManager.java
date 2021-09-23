package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuManager extends InventoryManager{
	private List<Menu> menu;
	
	public MenuManager(String menuName, double menuPrice) {
		menu = new ArrayList<Menu>();
	}
	
	public void addToMenu(Menu dish) {
		menu.add(dish);
	}
	
	public void createMenu(String dishName,ArrayList<String> ingredientsNames,ArrayList<Double> ingredientsQT,double dishPrice) {
		boolean sameName=checkForSameMenuName(dishName);
		
		if(sameName==false) {
			Menu newMenu = new Menu(dishName,dishPrice,ingredientsNames,ingredientsQT);
			addToMenu(newMenu);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The Dish has been created!");
			alert.showAndWait();
		}
		else if(sameName==true) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("There is already a Dish with that name");

			alert.showAndWait();
		}

	}

	public boolean checkForSameMenuName(String dishName) {
		boolean sameName=false;
		
		for (int j =0; j<menu.size() && !sameName;j++) {
			if(j>0) {
				int f=j;
				do {
					if(dishName.equalsIgnoreCase(menu.get(f-1).getMenuName())) {
						sameName=true;
					}
					f--;
				}while(f>0);
			}
			else if(dishName.equalsIgnoreCase(menu.get(j).getMenuName())) {
				sameName=true;
			}
		}
		
		return sameName;
	}
}
