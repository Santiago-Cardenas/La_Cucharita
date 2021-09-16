package model;

public class Ingredients {

	private String ingredientName;
	private int ingredientQT;
	private String ingredientUnits;
	
	protected Ingredients(String ingredientName, int ingredientQT, String ingredientUnits) {
		this.ingredientName = ingredientName;
		this.ingredientQT = ingredientQT;
		this.ingredientUnits = ingredientUnits;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public int getIngredientQT() {
		return ingredientQT;
	}
	public void setIngredientQT(int ingredientQT) {
		this.ingredientQT = ingredientQT;
	}
	public String getIngredientUnits() {
		return ingredientUnits;
	}
	public void setIngredientUnits(String ingredientUnits) {
		this.ingredientUnits = ingredientUnits;
	}

	
	
	
	
}