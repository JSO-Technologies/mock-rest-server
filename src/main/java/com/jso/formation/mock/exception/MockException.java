package com.jso.formation.mock.exception;

import java.text.MessageFormat;

import javax.ws.rs.core.Response;

public class MockException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private final ExceptionType type;
	private final Object[] details;
	
	public MockException(final ExceptionType type, final Object... details) {
		this.type = type;
		this.details = details;
	}
	
	public Response getResponse() {
		final String message = details == null ? type.getMessage() : MessageFormat.format(type.getMessage(), details);
		return Response.status(type.getStatus()).entity(message).build();
	}
}
