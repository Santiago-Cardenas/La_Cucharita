package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserManager {

	private List<User> users;

	public UserManager() {
		users = new ArrayList <User>();
		User admin = new User("1","123","000","27-03-2002");
		users.add(admin);
	}

	public List<User> getUsers() {
		return users;
	}

	public void  add(User newUser) {
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
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Incorrect password");

					alert.showAndWait();
					incorrect=true;
				}
			}
		}

		if(incorrect==false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Incorrect Id");

			alert.showAndWait();
		}
		return found;
	}

	public void changePassword(String id,String newPassword) {
		for(int i=0; i<users.size();i++) {
			if(id.equalsIgnoreCase(users.get(i).getId())){
				users.get(i).setPassword(newPassword);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Password successfully changed!");

				alert.showAndWait();
			}
		}
	}
	
	public String toString() {
		String msg="NAME	ID		BIRTHDAY	PASSWORD\n";
		
		for (int i=0; i<users.size();i++) {
			msg+=users.get(i).getUsername() + "	" + users.get(i).getId() + "	" + users.get(i).getBirthDay() + "	" +users.get(i).getPassword() + "\n";
		}
		return msg;
	}

}
