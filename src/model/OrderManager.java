package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OrderManager {
	
public List<Order> order;
	
	public OrderManager() {
		order = new ArrayList<Order>();
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public void addToOrders(Order neworder) {
		order.add(neworder);
	}
	
	public void createOrder(ArrayList<Menu> menusRequested,LocalDate facturationDate,boolean okToCreate) {
		boolean okToCreateOrder=okToCreate;
		if(okToCreateOrder==true) {
			@SuppressWarnings("unchecked")
			ArrayList<Menu> menuToStore = (ArrayList<Menu>)menusRequested.clone();
			Order newOrder = new Order(menuToStore,OrderState.PENDING,facturationDate);
			addToOrders(newOrder);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Order placed");

			alert.showAndWait();
		}
	}
	
	public void insertionSortCode() {

		Order aux;
		int j;
		for(int i = 1; i <order.size();i++){
			aux = order.get(i); 
			j = i-1;

			while( (j >= 0)  && (aux.getOrderState().getValue() < order.get(j).getOrderState().getValue()) ){
				order.set(j+1, order.get(j));
				j--;
				//printArray();
			}
			order.set(j+1, aux);
		}

	}
	
}
