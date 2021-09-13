package model;

public class User {
	private String username,password,birthDay,id;
	public User(String name, String password, String id, String birthDay) {
		this.username = name;
		this.password = password;
		this.id = id;
		this.birthDay = birthDay;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	
}
