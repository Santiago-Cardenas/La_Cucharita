package model;

public enum OrderState{

	PENDING(1),ON_GOING(2),DELIVERED(3);

	private final int id;
	
	OrderState(int id) { 
		this.id = id; 
	}
	public int getValue() { 
		return id; 
	}

}