package com.jso.formation.mock.filter;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import com.jso.formation.mock.session.SessionManager;

public class SessionManagerResponseFilter implements ContainerResponseFilter {

	private static final String SET_COOKIE = "Set-Cookie";

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		setHeader(requestContext, responseContext);
		
		addCookiesInHeader(requestContext, responseContext);
		
		SessionManager.getInstance().clearSession();
	}

	private void setHeader(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
		final List<String> origin = requestContext.getHeaders().get("origin");
		if(origin != null && !origin.isEmpty()) {
			responseContext.getHeaders().add("Access-Control-Allow-Origin", requestContext.getHeaders().get("origin").get(0));
		}
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	}

	private void addCookiesInHeader(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
		responseContext.getHeaders().add(SET_COOKIE, SessionManager.getInstance().getCookie(requestContext));
	}
	
}
