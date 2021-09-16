package model;

public class Beans extends Ingredients{
	
	private int beansGrams;
	
	public Beans(String ingredientName,int beansGrams) {
		super(ingredientName);
		this.beansGrams=beansGrams;
	}

	public int getBeansGrams() {
		return beansGrams;
	}

	public void setBeansGrams(int beansGrams) {
		this.beansGrams = beansGrams;
	}
	
	
}
