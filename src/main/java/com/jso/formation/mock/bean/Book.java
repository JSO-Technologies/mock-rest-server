package com.jso.formation.mock.bean;

import org.json.JSONObject;

public class Book {
	private String id;
	private String title;
	private String author;
	private String language;
	private int year;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public static Book fromJSON(final JSONObject json) {
		final JSONObject details = json.getJSONObject("fields");
		
		final Book book = new Book();
		book.setId(json.optString("recordid"));
		book.setTitle(details.optString("titre_original"));
		book.setAuthor(details.optString("auteur_personne_physique"));
		book.setLanguage(details.optString("langue"));
		book.setYear(Integer.parseInt(details.optString("annee_d_edition")));
		
		return book;
	}
}
