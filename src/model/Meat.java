package model;

public class Meat  extends Ingredients{
	
	private int meatKg;
	
	public Meat(String ingredientName, int meatKg) {
		super(ingredientName);
		this.meatKg=meatKg;
	}

	public int getMeatKg() {
		return meatKg;
	}

	public void setMeatKg(int meatKg) {
		this.meatKg = meatKg;
	}
	
}
