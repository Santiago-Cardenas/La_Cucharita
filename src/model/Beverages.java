package model;

public class Beverages extends Ingredients {

	private int beverageML;

	public Beverages(String ingredientName,int beverageML) {
		super(ingredientName);
		this.beverageML=beverageML;
	}

	public int getBeverageML() {
		return beverageML;
	}

	public void setBeverageML(int beverageML) {
		this.beverageML = beverageML;
	}
	
	
}
