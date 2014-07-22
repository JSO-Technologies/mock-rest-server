package com.jso.formation.mock.dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.json.JSONObject;

import com.jso.formation.mock.bean.User;

public class UserDAO {
	private static final String FILENAME = "/users.json";
	private static final List<User> users = new ArrayList<>();
	
	static {
		try {
			final URI uri = BookDAO.class.getResource(FILENAME).toURI();
			final Path filePath = Paths.get(uri);
		
			try(Stream<String> lines = Files.lines(filePath)) {
				lines.forEach(line -> {
					final JSONObject json = new JSONObject(line);
					users.add(User.fromJSON(json));
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static void create(final User user) {
		user.setId(UUID.randomUUID().toString());
		users.add(user);
	}

	public static User find(final String username, final String password) {
		final Optional<User> opt = users.stream()
				.filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
				.findFirst();
		return opt.orElse(null);
	}

	public static User findById(final String userId) {
		final Optional<User> opt = users.stream()
				.filter(user -> user.getId().equals(userId))
				.findFirst();
		return opt.orElse(null);
	}
}
