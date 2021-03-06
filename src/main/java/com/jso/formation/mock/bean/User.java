package com.jso.formation.mock.bean;

import com.jso.formation.mock.api.UserInfosResponse;
import org.json.JSONObject;

public class User {
	private String id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private boolean deleted;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	public static User fromJSON(JSONObject json) {
		final User user = new User();
		user.setId(json.optString("id"));
		user.setUsername(json.optString("username"));
		user.setFirstname(json.optString("firstname"));
		user.setLastname(json.optString("lastname"));
		user.setPassword(json.optString("password"));
		user.setDeleted(json.optBoolean("deleted", false));
		
		return user;
	}

	public static UserInfosResponse publicUserFromUser(final User user) {
		final UserInfosResponse result = new UserInfosResponse();
		result.setId(user.getId());
		result.setUsername(user.getUsername());
		result.setFirstname(user.getFirstname());
		result.setLastname(user.getLastname());

		return result;
	}
}
