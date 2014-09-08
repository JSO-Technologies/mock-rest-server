package com.jso.formation.mock.api;

public class UserResponse {
	private String username;

	public UserResponse(final String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
