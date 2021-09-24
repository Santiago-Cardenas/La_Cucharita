package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InventoryManager {
	
	public List<Ingredient> ingredients;
	
	public InventoryManager()
	{
		ingredients = new ArrayList<Ingredient>();
	}

	public List<Ingredient> getIngredients()
	{
		return ingredients;
	}
	
	public void addIngredient( Ingredient newIngredient ){
		boolean alreadyExists= ingredientAlreadyExists(newIngredient);
		if(alreadyExists==false) {
			ingredients.add(newIngredient);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The ingredient has been stored!");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("This ingredient already exits");
			alert.showAndWait();
		}
	}
	
	public boolean ingredientAlreadyExists(Ingredient newIngredient) {
		boolean alreadyExists=false;
		int count=0;
		for(int i=0;i<ingredients.size();i++) {
			if(ingredients.get(i).getIngredientName().equalsIgnoreCase(newIngredient.getIngredientName())) {
				count++;
			}
		}
		if(count>1) {
			alreadyExists=true;
		}
		return alreadyExists;
	}
	
	public int findIngredient( String ingredientName )
	{
		int index = 0;
		boolean found = false;
		
		for( int i = 0; i < ingredients.size() && !found; i++  )
		{
			if( ingredientName.equalsIgnoreCase( ingredients.get(i).getIngredientName() ) )
			{
				index = i;
				found = true;
			}
			else
			{
				index = -1;
			}
		}
		
		return index;
	}
	
	public void deleteIngredient( String ingredientName ) 
	{
		if( findIngredient( ingredientName ) != -1 )
		{
			ingredients.remove(findIngredient( ingredientName ));
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The ingredient does'nt exist");

			alert.showAndWait();
		}
	}
	
	public void increaseIngredient( String ingredientName, double amountToIncrease )
	{
		int ingredientIndex = findIngredient(ingredientName);
		
		if( findIngredient( ingredientName ) != -1 )
		{
			double newIngredientAmount = ingredients.get(ingredientIndex).getIngredientQT() + amountToIncrease;
			
			ingredients.get(ingredientIndex).setIngredientQT(newIngredientAmount); 
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The ingredient does'nt exist");

			alert.showAndWait();
		}
		

	}
	
	public void decreaseIngredient(String ingredientName, double amountToDecrease )
	{
		int ingredientIndex = findIngredient(ingredientName);
		
		if( findIngredient( ingredientName ) != -1 )
		{
					
			double newIngredientAmount = ingredients.get(ingredientIndex).getIngredientQT() - amountToDecrease;
					
			ingredients.get(ingredientIndex).setIngredientQT(newIngredientAmount); 
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The ingredient does'nt exist");

			alert.showAndWait();
		}

	}
	/*
	public String toString()
	{

		String info ="Ingredient		Amount			Units\n";
			
		for (int i=0; i<ingredients.size();i++) 
		{
			info+=ingredients.get(i).getIngredientName() + "	" + ingredients.get(i).getIngredientQT() + "	" + ingredients.get(i).getIngredientUnits() +  "\n";
		}
			
		return info;
	}
	*/
}
