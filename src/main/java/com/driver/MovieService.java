package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }
    public String addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair(String directorName, String movieName) {
        return movieRepository.addMovieDirectorPair(directorName,movieName);
    }

    public Movie getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String directorName) {
        return movieRepository.deleteDirectorByName(directorName);
    }

    public String deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}
