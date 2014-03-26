package com.jso.formation.mock.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;

import com.jso.formation.mock.session.SessionManager;

public class SessionManagerRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		SessionManager.getInstance().clearSession();
		
		Cookie cookie = requestContext.getCookies().get(SessionManager.MOCK_COOKIE);
		if (cookie != null) {
			SessionManager.getInstance().bind(cookie.getValue());
		}
	}
	
}
