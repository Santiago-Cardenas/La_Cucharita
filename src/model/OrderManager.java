package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OrderManager {
	
public List<Order> order;
private InventoryManager inventoryManager;
	
	public OrderManager() {
		order = new ArrayList<Order>();
		inventoryManager= new InventoryManager();
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
	
	public void createOrder(ArrayList<Menu> menusRequested,String facturationDate,boolean okToCreate) {
		boolean okToCreateOrder=okToCreate;
		
		if(okToCreateOrder==true) {
			Order newOrder = new Order(menusRequested,OrderState.PENDING,facturationDate);
			addToOrders(newOrder);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Order placed");

			alert.showAndWait();
		}

	}
}
