package com.jso.formation.mock.service.book;

import static javax.ws.rs.core.Response.Status.NOT_MODIFIED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.jso.formation.mock.api.BookListResponse;
import com.jso.formation.mock.bean.Book;
import com.jso.formation.mock.dao.BookDAO;
import com.jso.formation.mock.session.SessionManager;

@Path("books")
public class BookService {
	
	@GET
	@Path("list")
	public Response getBooks(@QueryParam("page") final Integer page) {
		if(! SessionManager.getInstance().isAuthenticated()) {
			return Response.status(UNAUTHORIZED).build();
		}
		
		final Integer adaptedPage = page == null || page == 0 ? 1 : page;
		final List<Book> books = BookDAO.getBooksOnPage(adaptedPage);
		final Integer nbPage = BookDAO.getNB_PAGES();
		final BookListResponse response = new BookListResponse(adaptedPage, nbPage, books);
		
		return Response.status(NOT_MODIFIED).entity(response).build();
	}
}
