package com.jso.formation.mock.dao;

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
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
