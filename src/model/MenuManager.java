package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuManager{
	public List<Menu> menu;
	
	public MenuManager() {
		menu = new ArrayList<Menu>();
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
			newMenu.setTotalQTRequested(0);
			newMenu.setTotalMoneyPaid(0);
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
	
	public int findMenu( String menuName )
	{
		int index = 0;
		boolean found = false;
		
		for( int i = 0; i < menu.size() && !found; i++  )
		{
			if( menuName.equalsIgnoreCase( menu.get(i).getMenuName() ) )
			{
				index = i;
				found = true;
			}
			else
			{
				index = -1;
			}
		}
		
		return index;
	}
}
