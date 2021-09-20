package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InventoryManager {
	
	private List<Ingredient> ingredients;
	
	public InventoryManager()
	{
		ingredients = new ArrayList<Ingredient>();
	}

	public List<Ingredient> getIngredients()
	{
		return ingredients;
	}
	
	public void addIngredient( Ingredient newIngredient )
	{
		ingredients.add(newIngredient);
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
		
		double newIngredientAmount = ingredients.get(ingredientIndex).getIngredientQT() + amountToIncrease;
				
		ingredients.get(ingredientIndex).setIngredientQT(newIngredientAmount); 
	}
	
	public void decreaseIngredient(String ingredientName, double amountToDecrease )
	{
		int ingredientIndex = findIngredient(ingredientName);
		
		double newIngredientAmount = ingredients.get(ingredientIndex).getIngredientQT() - amountToDecrease;
				
		ingredients.get(ingredientIndex).setIngredientQT(newIngredientAmount); 
	}
	
	
}
