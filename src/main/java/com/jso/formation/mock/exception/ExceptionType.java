package com.jso.formation.mock.exception;

import javax.ws.rs.core.Response.Status;

public class ExceptionType {
	private final Status status;
	private final String message;
	
	public ExceptionType(final Status status, final String message) {
		this.status = status;
		this.message = message;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
}
