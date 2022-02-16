package com.movie.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.jpa.MovieRepository;
import com.movie.model.Movie;

@RestController
public class MyRestController {

	private Movie[] movies;

	private MovieRepository movieRepository;

	@Autowired
	public MyRestController(Movie[] movies, MovieRepository movieRepository) {
		this.movies = movies;
		this.movieRepository = movieRepository;
	}

	@GetMapping("/retriveMovie")
	public Movie[] retriveAllMovies() {
		return movieRepository.findAllByOrderByTitleAsc();
	}

	@GetMapping("/findMovie")
	public Optional<Movie> findMovie(@RequestParam("id") String id) {
		return movieRepository.findById(id.toLowerCase());

	}

	@GetMapping("/orderBy")
	public Movie[] orderBy(@RequestParam("id") String id) {

		if (id.equals("name")) {
			// Arrays.sort(this.movies, Comparator.comparing(Movie::getTitle)); Senza
			// Hibernate Method
			return movieRepository.findAllByOrderByTitleAsc();
		}
		if (id.equals("rate")) {
			return movieRepository.findAllByOrderByYearAsc();
		}
		if (id.equals("year")) {

			return movieRepository.findAllByOrderByRatingAsc();
		}

		return movies;
	}

}
