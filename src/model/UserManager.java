package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserManager {

	public List<User> users;

	public UserManager() {
		users = new ArrayList <User>();
		User admin = new User("000","1","000","27-03-2002");
		users.add(admin);
	}

	public List<User> getUsers() {
		return users;
	}

	public void  addNewUser(User newUser) {
		users.add(newUser);
	}

	public boolean accountLogIn(String id,String password) {
		boolean found=false;
		boolean incorrect=false;
		for(int i=0; i<users.size();i++) {
			
			if(id.equals(users.get(i).getId())) {
				
				if(password.equals(users.get(i).getPassword())) {
					found=true;
					incorrect=true;
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Welcome!");

					alert.showAndWait();
				}
				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Incorrect password");

					alert.showAndWait();
					incorrect=true;
				}
			}
		}

		if(incorrect==false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Incorrect Id");

			alert.showAndWait();
		}
		return found;
	}

	public void changePassword(String id,String oldPassword,String newPassword) {
		boolean idFound=false;
		for(int i=0; i<users.size();i++) {
			if(id.equalsIgnoreCase(users.get(i).getId())){
				if(oldPassword.equalsIgnoreCase(users.get(i).getPassword())) {
					users.get(i).setPassword(newPassword);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Password successfully changed!");

					alert.showAndWait();

				}
				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Incorrect password");

					alert.showAndWait();
				}
				idFound=true;
			}
		}
		
		if(idFound==false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The Id does not exist");

			alert.showAndWait();
		}
	}
	
	public void sortById() {
		int j;
		User aux;
		for(int i=1;i<users.size();i++) {
			aux= users.get(i);
			j=i-1;
			while(j>=0 && (aux.compareByUserID(users.get(j)) < 0)) {
				users.set(j+1, users.get(j));
				j--;
			}
			users.set(j+1, aux);
		}
	}

}
