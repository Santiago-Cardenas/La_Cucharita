package model;

public class User {
	
	
	private String username,password,birthDay,id;
	private int pedidosEntregados;
	private double dineroTotalDeCombosVendidos;
	
	
	
	public User(String id,String name, String password, String birthDay) 
	{
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
	
	public int compareByUserID(User otherUser) {
		return id.compareTo(otherUser.getId());
	}

	public int getPedidosEntregados() {
		return pedidosEntregados;
	}

	public void setPedidosEntregados(int pedidosEntregados) {
		this.pedidosEntregados = pedidosEntregados;
	}

	public double getDineroTotalDeCombosVendidos() {
		return dineroTotalDeCombosVendidos;
	}

	public void setDineroTotalDeCombosVendidos(double dineroTotalDeCombosVendidos) {
		this.dineroTotalDeCombosVendidos = dineroTotalDeCombosVendidos;
	}
	
}
