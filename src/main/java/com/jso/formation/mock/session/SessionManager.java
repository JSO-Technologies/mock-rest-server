package com.jso.formation.mock.session;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.NewCookie;

public class SessionManager {
	public static final String MOCK_COOKIE = "jso_mock_cookie";

	private static final SessionManager INSTANCE = new SessionManager();
	

	private final ThreadLocal<Session> threadSession = new ThreadLocal<Session>() {

		@Override
		protected Session initialValue() {
			return null;
		}

	};
	
	private SessionManager() {}
	
	public static SessionManager getInstance() {
		return INSTANCE;
	}
	
	public Session getSession() {
		return threadSession.get();
	}
	
	public void clearSession() {
		threadSession.set(null);
	}
	
	public void initSession(final String userId) {
		setSession(new Session(userId));
	}
	
	public void setSession(Session session) {
		threadSession.set(session);
	}

	public void bind(String userId) {
		threadSession.set(new Session(userId));
	}

	public boolean isAuthenticated() {
		return getSession() != null;
	}

	/**
	 * Create cookie from session
	 * @param requestContext
	 * @return
	 */
	public NewCookie getCookie(ContainerRequestContext requestContext) {
		return CookieEncoder.getInstance().sessionToCookie(getSession(), requestContext);
	}
}
