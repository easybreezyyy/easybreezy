package application;

import java.io.Serializable;

public class Member implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String store;
	private String position;
	private String phone;
	
	public Member() {}

	public Member(String username, String password, String store, String position, String phone) {
		this.username = username;
		this.password = password;
		this.store = store;
		this.position = position;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Member [username=" + username + ", password=" + password + ", store=" + store + ", position=" + position
				+ ", phone=" + phone + "]";
	}

	
	
	
	
}
