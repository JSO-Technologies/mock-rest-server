package com.jso.formation.mock.api;

import javax.ws.rs.FormParam;

public class UserRegisterRequest {
	@FormParam("username")
	private String username;
	@FormParam("firstname")
	private String firstname;
	@FormParam("lastname")
	private String lastname;
	@FormParam("password")
	private String password;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}	
