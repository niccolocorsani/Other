package com.movie.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Movie {

	@Id
	private String title;
	private int year;
	private int rating;
	private String description;

	public Movie() {
		super();
	}

	public Movie(String title, int year, int rating, String description) {
		super();
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.toLowerCase();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
