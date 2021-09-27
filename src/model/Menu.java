package model;

import java.util.ArrayList;
import java.util.List;

public class Menu{
	private String menuName;
	private double menuPrice;
	private int menuQTRequested;
	private int totalQTRequested;
	private double totalMoneyPaid;
	
	public ArrayList<Ingredient> ingredientsUsed= new ArrayList<Ingredient>();;
	public Menu(String menuName, double menuPrice, ArrayList<Ingredient> ingredientsUsed) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.ingredientsUsed = ingredientsUsed;
	}
	
	public Menu(String menuName, int menuQTRequested) {
		this.menuName = menuName;
		this.menuQTRequested=menuQTRequested;
	}
	
	public String getMenuName() {
		return menuName;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public double getMenuPrice() {
		return menuPrice;
	}
	
	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}

	public List<Ingredient> getIngredientsUsed() {
		return ingredientsUsed;
	}

	public void setIngredientsUsed(ArrayList<Ingredient> ingredientsUsed) {
		this.ingredientsUsed = ingredientsUsed;
	}

	public int getMenuQTRequested() {
		return menuQTRequested;
	}

	public void setMenuQTRequested(int menuQTRequested) {
		this.menuQTRequested = menuQTRequested;
	}

	public int getTotalQTRequested() {
		return totalQTRequested;
	}

	public void setTotalQTRequested(int totalQTRequested) {
		this.totalQTRequested = totalQTRequested;
	}

	public double getTotalMoneyPaid() {
		return totalMoneyPaid;
	}

	public void setTotalMoneyPaid(double totalMoneyPaid) {
		this.totalMoneyPaid = totalMoneyPaid;
	}
	
	
	
}