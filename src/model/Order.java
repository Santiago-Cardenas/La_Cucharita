package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Order{
	private OrderState orderState;
	private String code;
	private LocalDate orderDate;
	public ArrayList<Menu> menusRequested= new ArrayList<Menu>();
	
	public Order(ArrayList<Menu> menusRequested, OrderState orderState, LocalDate orderDate) {
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

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	
}

