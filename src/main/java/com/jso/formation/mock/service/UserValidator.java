package com.jso.formation.mock.service;

import static org.apache.commons.lang.StringUtils.isBlank;

import com.jso.formation.mock.api.UserLoginRequest;
import com.jso.formation.mock.api.UserRegisterRequest;
import com.jso.formation.mock.exception.MockException;
import com.jso.formation.mock.exception.UserExceptionTypes;

public class UserValidator {
	public static void validate(final UserRegisterRequest request) throws MockException {
		if(isBlank(request.getUsername())) {
			throw new MockException(UserExceptionTypes.MISSING_PARAMETER, "username");
		}
		if(isBlank(request.getPassword())) {
			throw new MockException(UserExceptionTypes.MISSING_PARAMETER, "password");
		}
		if(isBlank(request.getFirstname())) {
			throw new MockException(UserExceptionTypes.MISSING_PARAMETER, "firstname");
		}
		if(isBlank(request.getLastname())) {
			throw new MockException(UserExceptionTypes.MISSING_PARAMETER, "lasname");
		}
	}

	public static void validate(UserLoginRequest request) throws MockException {
		if(isBlank(request.getUsername())) {
			throw new MockException(UserExceptionTypes.MISSING_PARAMETER, "username");
		}
		if(isBlank(request.getPassword())) {
			throw new MockException(UserExceptionTypes.MISSING_PARAMETER, "password");
		}
	}
}
