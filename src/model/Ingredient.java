package model;

public class Ingredient {

	private String ingredientName;
	private double ingredientQT;
	private String ingredientUnits;
	
	
	protected Ingredient(String ingredientName, double ingredientQT, String ingredientUnits) 
	{
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
	public double getIngredientQT() {
		return ingredientQT;
	}
	public void setIngredientQT(double ingredientQT) {
		this.ingredientQT = ingredientQT;
	}
	public String getIngredientUnits() {
		return ingredientUnits;
	}
	public void setIngredientUnits(String ingredientUnits) {
		this.ingredientUnits = ingredientUnits;
	}

	
	
	
	
}
