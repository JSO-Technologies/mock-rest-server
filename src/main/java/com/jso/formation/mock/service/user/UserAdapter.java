package com.jso.formation.mock.service.user;

import com.jso.formation.mock.api.UserRegisterRequest;
import com.jso.formation.mock.bean.User;

public class UserAdapter {
	public static User adaptToEntity(final UserRegisterRequest request) {
		final User user = new User();
		user.setUsername(request.getUsername().toLowerCase());
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setPassword(request.getPassword());
		
		return user;
	}
}
