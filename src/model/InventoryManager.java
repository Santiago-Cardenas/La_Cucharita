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
		if(count>=1) {
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
		if(  findIngredient( ingredientName ) != -1  )
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
	
	public void increaseIngredient( String ingredientName, String amountToIncrease )
	{
		int ingredientIndex = findIngredient(ingredientName);
		double amountToIncreaseNum = Double.parseDouble(amountToIncrease);
		if(  findIngredient( ingredientName ) != -1  )
		{
			double newIngredientAmount = ingredients.get(ingredientIndex).getIngredientQT() + amountToIncreaseNum;
			
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
	
	public void decreaseIngredient(String ingredientName, String amountToDecrease )
	{
		int ingredientIndex = findIngredient(ingredientName);
		double amountToDecreaseNum = Double.parseDouble(amountToDecrease);
		
		if( findIngredient( ingredientName ) != -1  )
		{
			if(( ingredients.get( findIngredient(ingredientName)).getIngredientQT() >= amountToDecreaseNum) )
			{
				double newIngredientAmount = ingredients.get(ingredientIndex).getIngredientQT() - amountToDecreaseNum;
					
				ingredients.get(ingredientIndex).setIngredientQT(newIngredientAmount); 
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Not enough quantity");

				alert.showAndWait();
			}
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
	//Selection
	public void sortByQuantity()
	{
		int pos = 0;
		while(pos < ingredients.size())
		{
			Ingredient max = ingredients.get(pos);
		
			for(int i = pos+1; i < ingredients.size(); i++)
			{
				if( ingredients.get(i).getIngredientQT() > max.getIngredientQT())
				{
					Ingredient temp = ingredients.get(i);
					ingredients.set(i,max);
					max = temp;
				}
			}
			ingredients.set(pos, max);
			pos++;
		}
	}
}
