package com.jso.formation.mock.session;

import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.NewCookie;

public class CookieEncoder {
	private static CookieEncoder INSTANCE = new CookieEncoder();

	private CookieEncoder() {
	}

	public static CookieEncoder getInstance() {
		return INSTANCE;
	}

	public Session cookieToSession(String cookie_value) {
		return new Session(cookie_value);
	}

	public NewCookie sessionToCookie(Session session, ContainerRequestContext requestContext) {
		String token_value = null;
		Date expirationDate = null;
		int cookieDuration = 0;
		if(session != null) {
			token_value = session.getUserId();
			expirationDate = new Date(new Date().getTime() + 1000 * 60 * 60);
			cookieDuration = 1000 * 60 * 60;
		}

		return new NewCookie(
				SessionManager.MOCK_COOKIE, 
				token_value, 
				"/", 
				requestContext.getUriInfo().getBaseUri().getHost(), 
				1,
				null, 
				cookieDuration,
				expirationDate,
				false, //is secured 
				true);
	}
}
