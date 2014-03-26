package com.jso.formation.mock.session;


public class Session {
	private final String userId;
	
	public Session(final String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
}
