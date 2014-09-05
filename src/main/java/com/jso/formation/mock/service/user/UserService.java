package com.jso.formation.mock.service.user;

import static com.jso.formation.mock.exception.UserExceptionTypes.USER_NOT_FOUND;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.GONE;
import static javax.ws.rs.core.Response.Status.NOT_MODIFIED;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jso.formation.mock.api.UserLoginRequest;
import com.jso.formation.mock.api.UserRegisterRequest;
import com.jso.formation.mock.bean.User;
import com.jso.formation.mock.dao.UserDAO;
import com.jso.formation.mock.exception.MockException;
import com.jso.formation.mock.session.Session;
import com.jso.formation.mock.session.SessionManager;

@Path("user")
public class UserService {
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("register")
	public Response createUser(final UserRegisterRequest request) {
		try {
			UserValidator.validate(request);
			final User user = UserAdapter.adaptToEntity(request);
			
			UserDAO.create(user);
			SessionManager.getInstance().initSession(user.getId());
			
			return Response.status(CREATED).build();
		}
		catch(MockException e) {
			return e.getResponse();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("session")
	public Response login(final UserLoginRequest request) {
		try {
			UserValidator.validate(request);
			
			final User user = UserDAO.find(request.getUsername().toLowerCase(), request.getPassword());
			if(user == null) {
				throw new MockException(USER_NOT_FOUND);
			}
			
			SessionManager.getInstance().initSession(user.getId());
			return Response.status(OK).build();
		}
		catch(MockException e) {
			return e.getResponse();
		}
	}
	
	@GET
	@Path("session")
	public Response getSession() {
		if(SessionManager.getInstance().isAuthenticated()) {
			return Response.status(OK).build();
		}
		else {
			return Response.status(UNAUTHORIZED).build();
		}
	}
	
	@DELETE
	@Path("session")
	public Response deleteSession() {
		SessionManager.getInstance().clearSession();
		return Response.status(GONE).build();
	}
	
	@GET
	@Path("infos")
	public Response getUserInfos() {
		if(!SessionManager.getInstance().isAuthenticated()) {
			return Response.status(UNAUTHORIZED).build();
		}
		
		final Session session = SessionManager.getInstance().getSession();
		final User user = UserDAO.findById(session.getUserId());
		return Response.status(NOT_MODIFIED).entity(user).build();
	}
}
