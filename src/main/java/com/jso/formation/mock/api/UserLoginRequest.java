package com.jso.formation.mock.api;

import javax.ws.rs.FormParam;

public class UserLoginRequest {
	@FormParam("username")
	private String username;
	@FormParam("password")
	private String password;

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
}
