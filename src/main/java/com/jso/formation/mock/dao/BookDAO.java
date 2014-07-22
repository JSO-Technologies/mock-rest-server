package com.jso.formation.mock.dao;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jso.formation.mock.bean.Book;



public class BookDAO {
	private static final String FILENAME = "/books.json";
	private static final List<Book> books = new ArrayList<>();
	private static final int NB_PER_PAGE = 20;
	private static int NB_PAGES;
	
	static {
		try {
			final URI uri = BookDAO.class.getResource(FILENAME).toURI();
			final Path filePath = Paths.get(uri);
		
			try(Stream<String> lines = Files.lines(filePath)) {
				lines.forEach(line -> {
					final JSONArray bookArray = new JSONArray(line);
					for(int i = 0; i < bookArray.length(); ++i) {
						final JSONObject json = bookArray.getJSONObject(i);
						books.add(Book.fromJSON(json));
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			NB_PAGES = books.size() / NB_PER_PAGE + (books.size() % NB_PER_PAGE == 0 ? 0 : 1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static int getNB_PAGES() {
		return NB_PAGES;
	}

	public static List<Book> getBooksOnPage(final Integer page) {
		final Integer realPage = page > NB_PAGES ? NB_PAGES : page - 1;
		return books.stream()
				.skip(NB_PER_PAGE * realPage)
				.limit(NB_PER_PAGE)
				.collect(toList());
	}
}
