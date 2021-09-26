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
	
	public void createOrder(ArrayList<Menu> menusRequested,String facturationDate) {
		boolean okToCreateOrder=checkIfServingIsPosible(menusRequested);
		
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

	public boolean checkIfServingIsPosible(ArrayList<Menu> menusRequested) {
		boolean canServe=false;
		String msg="";
		double currentIngredientsQT=0;
		double requestedIngredientsQT=0;
		double ingredientsLeft=0;
		int menuRequestedQT=0;
		for (int i =0; i<menusRequested.size();i++) {
			System.out.println("Entro al for de menu");
			menuRequestedQT=menusRequested.get(i).getMenuQTRequested();
			System.out.println(menuRequestedQT + "menuQT");
			msg+="For the " + menusRequested.get(i).getMenuName() + " dish\n";
			for(int j=0;j<menusRequested.get(i).getIngredientsUsed().size() && !canServe;j++) {
				System.out.println("Entro al for de ingredientes");
				currentIngredientsQT=inventoryManager.getIngredients().get(j).getIngredientQT();
				requestedIngredientsQT= menuRequestedQT * menusRequested.get(i).getIngredientsUsed().get(j).getIngredientQT();
				ingredientsLeft=currentIngredientsQT-requestedIngredientsQT;
				System.out.println(ingredientsLeft + "sobrantes");
				if(ingredientsLeft<0) {
					System.out.println("Entro al if de cuando no hay suficientes ingredientes");
					msg+="There is not enough: \n" + menusRequested.get(i).getIngredientsUsed().get(j).getIngredientName() + "\n";
					canServe=true;
				}
			}
			msg+="To make the order";
		}
		if(canServe=false) {
			for (int i =0; i<menusRequested.size();i++) {
				System.out.println(menuRequestedQT + "entro cuando serve es true");
				menuRequestedQT=menusRequested.get(i).getMenuQTRequested();
				System.out.println(menuRequestedQT + "menuQT");
				for(int j=0;j<menusRequested.get(i).getIngredientsUsed().size();j++) {
					requestedIngredientsQT= menuRequestedQT * menusRequested.get(i).getIngredientsUsed().get(j).getIngredientQT();
					System.out.println(requestedIngredientsQT + "requested Ingredient QT");
					System.out.println(requestedIngredientsQT + "Antes del cambio");
					System.out.println(inventoryManager.getIngredients().get(0).getIngredientName());
					System.out.println(inventoryManager.getIngredients().get(0).getIngredientQT());
					System.out.println(inventoryManager.getIngredients().get(0).getIngredientUnits());
					inventoryManager.decreaseIngredientQTBasedOnOrder(menusRequested.get(i).getIngredientsUsed().get(j).getIngredientName(),requestedIngredientsQT);
					System.out.println(requestedIngredientsQT + "Despues del cambio");
					System.out.println(inventoryManager.getIngredients().get(0).getIngredientName());
					System.out.println(inventoryManager.getIngredients().get(0).getIngredientQT());
					System.out.println(inventoryManager.getIngredients().get(0).getIngredientUnits());
				}
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText(msg);

			alert.showAndWait();
		}
		return canServe;
	}
}
