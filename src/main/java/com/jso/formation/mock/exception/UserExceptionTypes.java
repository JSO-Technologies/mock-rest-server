package com.jso.formation.mock.exception;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public interface UserExceptionTypes {
	ExceptionType MISSING_PARAMETER = new ExceptionType(BAD_REQUEST, "Missing parameter : {0}");
	ExceptionType USER_NOT_FOUND = new ExceptionType(NOT_FOUND, "User not found");
}
