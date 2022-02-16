package com.movie.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie,String>{

	
    public Movie[] findAllByOrderByTitleAsc();

    public Movie[] findAllByOrderByYearAsc();

    public Movie[] findAllByOrderByRatingAsc();
    

	
}
