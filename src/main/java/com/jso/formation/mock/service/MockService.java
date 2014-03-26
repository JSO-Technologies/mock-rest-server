package com.jso.formation.mock.service;

import static jersey.repackaged.com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

import com.jso.formation.mock.api.UserLoginRequest;
import com.jso.formation.mock.api.UserRegisterRequest;
import com.jso.formation.mock.session.Session;
import com.jso.formation.mock.session.SessionManager;

@Path("user")
public class MockService {
	private static final String LASTNAME = "lastname";
	private static final String FIRSTNAME = "firstname";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static Map<String, Map<String, String>> users = newHashMap();
	static {
		Map<String, String> user = newHashMap();
		user.put(USERNAME, "user1");
		user.put(PASSWORD, "password");
		user.put(FIRSTNAME, "Jimmy");
		user.put(LASTNAME, "Somsanith");
		users.put("1", user);
		user = newHashMap();
		user.put(USERNAME, "user2");
		user.put(PASSWORD, "password");
		user.put(FIRSTNAME, "Jean");
		user.put(LASTNAME, "Bon");
		users.put("2", user);
		user = newHashMap();
		user.put(USERNAME, "user3");
		user.put(PASSWORD, "password");
		user.put(FIRSTNAME, "Jessica");
		user.put(LASTNAME, "Cadanmonpantalon");
		users.put("3", user);
		user = newHashMap();
		user.put(USERNAME, "user4");
		user.put(PASSWORD, "password");
		user.put(FIRSTNAME, "Fella");
		user.put(LASTNAME, "Sion");
		users.put("4", user);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/register")
	public Response createUser(@BeanParam UserRegisterRequest request) {
		if(StringUtils.isBlank(request.getUsername())) {
			return Response.status(400).entity(USERNAME).build();
		}
		if(StringUtils.isBlank(request.getPassword())) {
			return Response.status(400).entity(PASSWORD).build();
		}
		if(StringUtils.isBlank(request.getFirstname())) {
			return Response.status(400).entity(FIRSTNAME).build();
		}
		if(StringUtils.isBlank(request.getLastname())) {
			return Response.status(400).entity(LASTNAME).build();
		}
		
		final String userId = String.valueOf(users.size());
		final Map<String, String> user = newHashMap();
		user.put(USERNAME, request.getUsername());
		user.put(PASSWORD, request.getPassword());
		user.put(FIRSTNAME, request.getFirstname());
		user.put(LASTNAME, request.getLastname());
		users.put(userId, user);
		
		Session session = new Session(userId);
		SessionManager.getInstance().setSession(session);
		
		return Response.status(200).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/session")
	public Response login(@BeanParam UserLoginRequest request) {
		final String username = request.getUsername();
		final String password = request.getPassword();
		
		if(StringUtils.isBlank(username)) {
			return Response.status(400).entity(USERNAME).build();
		}
		if(StringUtils.isBlank(password)) {
			return Response.status(400).entity(PASSWORD).build();
		}
		
		for(Entry<String, Map<String, String>> entry : users.entrySet()) {
			Map<String, String> userInfos = entry.getValue();
			if(userInfos.get(USERNAME).equals(username) && userInfos.get(PASSWORD).equals(password) ) {
				Session session = new Session(entry.getKey());
				SessionManager.getInstance().setSession(session);
				
				return Response.status(200).build();
			}
		}
		
		return Response.status(404).build();
	}
	
	@GET
	@Path("/session")
	public Response getSession() {
		if(SessionManager.getInstance().isAuthenticated()) {
			return Response.status(200).build();
		}
		else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/session")
	public Response deleteSession() {
		SessionManager.getInstance().clearSession();
		return Response.status(200).build();
	}
	
	@GET
	@Path("/infos")
	public Response getUserInfos() {
		if(!SessionManager.getInstance().isAuthenticated()) {
			return Response.status(401).build();
		}
		
		final String userId = SessionManager.getInstance().getSession().getUserId();
		return Response.status(200).entity(users.get(userId)).build();
	}
}
