package model;

import java.util.ArrayList;
import java.util.UUID;

public class Order{
	private OrderState orderState;
	private String code;
	private String orderDate;
	public ArrayList<Menu> menusRequested= new ArrayList<Menu>();
	
	public Order(ArrayList<Menu> menusRequested, OrderState orderState, String orderDate) {
		this.menusRequested=menusRequested;
		this.orderState=orderState;
		this.orderDate=orderDate;
		setCode(UUID.randomUUID().toString());
	}	

	public ArrayList<Menu> getMenusRequested() {
		return menusRequested;
	}

	public void setMenusRequested(ArrayList<Menu> menusRequested) {
		this.menusRequested = menusRequested;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	
}

