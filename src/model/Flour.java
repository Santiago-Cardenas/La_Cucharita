package model;

public class Flour  extends Ingredients{
	
	private int flourAmount;
	
	public Flour(String ingredientName, int flourAmount) {
		super(ingredientName);
		this.flourAmount=flourAmount;
	}

	public int getFlourAmount() {
		return flourAmount;
	}

	public void setFlourAmount(int flourAmount) {
		this.flourAmount = flourAmount;
	}

}
