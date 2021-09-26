package model;

public enum OrderState{

	PENDING(3),ON_GOING(2),DELIVERD(1);

	private final int id;
	
	OrderState(int id) { 
		this.id = id; 
	}
	public int getValue() { 
		return id; 
	}

}