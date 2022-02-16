package com.movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.movie.jpa.MovieRepository;
import com.movie.model.Movie;
import com.movie.parsingdata.JsonParser;

@SpringBootApplication
public class ApplicationBootStrap {

	public static void main(String[] args) {

		SpringApplication.run(ApplicationBootStrap.class, args);

	}

	@Bean
	public CommandLineRunner repositoryInitializer(MovieRepository movieRepository) {
		//// Functional Interface which provides Lambda Expression
		return (args) -> {
			Movie[] movies = JsonParser.parse(Movie.class.getResource("/films.json").getPath());
			for (Movie movie : movies)
				movieRepository.save(movie);

		};
	}
}