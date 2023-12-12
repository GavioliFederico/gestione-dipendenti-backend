package com.proj.finale.entity;

public class UserLoginRequest {
	
	private String email;
	
	private String password;
	
	//////////////////////Constructors/////////////////////////////
	
	public UserLoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	//////////////////////Setter-Getter/////////////////////////////
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
