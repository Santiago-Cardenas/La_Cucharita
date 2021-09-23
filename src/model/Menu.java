package model;

import java.util.ArrayList;
import java.util.List;

public class Menu{
	private String menuName;
	private double menuPrice;
	public List<String> ingredientsUsed = new ArrayList<String>();
	public List<Double> ingredientsQT= new ArrayList<Double>();
	public Menu(String menuName, double menuPrice, ArrayList<String> ingredientsUsed, ArrayList<Double> ingredientsQT) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.ingredientsUsed = ingredientsUsed;
		this.ingredientsQT = ingredientsQT;
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

	public List<String> getIngredientsUsed() {
		return ingredientsUsed;
	}

	public void setIngredientsUsed(List<String> ingredientsUsed) {
		this.ingredientsUsed = ingredientsUsed;
	}

	public List<Double> getIngredientsQT() {
		return ingredientsQT;
	}

	public void setIngredientsQT(List<Double> ingredientsQT) {
		this.ingredientsQT = ingredientsQT;
	}

	
	
}