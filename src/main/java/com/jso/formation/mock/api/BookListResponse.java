package com.jso.formation.mock.api;

import java.util.List;

import com.jso.formation.mock.bean.Book;

public class BookListResponse {
	private final Integer page;
	private final Integer nbPage;
	private final List<Book> books;
	
	public BookListResponse(final Integer page, final Integer nbPage, final List<Book> books) {
		this.page = page;
		this.nbPage = nbPage;
		this.books = books;
	}
	
	public Integer getPage() {
		return page;
	}
	
	public Integer getNbPage() {
		return nbPage;
	}
	
	public List<Book> getBooks() {
		return books;
	}
}
