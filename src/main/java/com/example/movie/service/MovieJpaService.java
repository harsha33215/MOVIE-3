/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.movie.service;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieJpaRepository;
import com.example.movie.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class MovieJpaService implements MovieRepository {
    @Autowired
    private MovieJpaRepository movieJpaRepository;

    @Override
    public ArrayList<Movie> getAllMovies() {
        List<Movie> movieList = movieJpaRepository.findAll();
        ArrayList<Movie> movie = new ArrayList<>(movieList);
        return movie;
    }

    @Override
    public Movie getMovieById(int movieId) {
        try {
            Movie movie = movieJpaRepository.findById(movieId).get();
            return movie;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Movie addMovie(Movie movie) {
        movieJpaRepository.save(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(int movieId, Movie movie) {
        try {
            Movie newMovie = movieJpaRepository.findById(movieId).get();
            if (movie.getMovieName() != null) {
                newMovie.setMovieName(movie.getMovieName());
            }
            if (movie.getLeadActor() != null) {
                newMovie.setLeadActor(movie.getLeadActor());
            }
            movieJpaRepository.save(newMovie);
            return newMovie;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteMovie(int movieId) {
        try {
            movieJpaRepository.deleteById(movieId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
