package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuManager extends InventoryManager{
	public List<Menu> menu;
	
	public MenuManager() {
		menu = new ArrayList<Menu>();
		ArrayList<Ingredient> ingredient = new ArrayList<Ingredient>();
		ingredient.add(ingredients.get(0));
		Menu newMenu = new Menu("combo 1", 10, ingredient);
		menu.add(newMenu);
	}
	
	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public void addToMenu(Menu dish) {
		menu.add(dish);
	}
	
	public void createMenu(String dishName,double dishPrice,ArrayList<Ingredient> ingredientsNames) {
		boolean sameName=checkForSameMenuName(dishName);
		@SuppressWarnings("unchecked")
		ArrayList<Ingredient> ingredientToStore = (ArrayList<Ingredient>)ingredientsNames.clone();
		if(sameName==false) {
			Menu newMenu = new Menu(dishName,dishPrice,ingredientToStore);
			addToMenu(newMenu);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The Dish has been created!");
			alert.showAndWait();
		}
		else if(sameName==true) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
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
