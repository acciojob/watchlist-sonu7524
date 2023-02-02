package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String,Movie> movieMap = new HashMap<>();
    Map<String,Director> directorMap = new HashMap<>();
    Map<String, List<String>> movieDirectorMap = new HashMap<>();

    public MovieRepository() {}

    public MovieRepository(Map<String, Movie> movieMap, Map<String, Director> directorMap, Map<String, List<String>> movieDirectorMap) {
        this.movieMap = movieMap;
        this.directorMap = directorMap;
        this.movieDirectorMap = movieDirectorMap;
    }

    public String addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
        return "Movie details successfully added";
    }

    public String addDirector(Director director) {
        directorMap.put(director.getName(),director);
        return "Director details successfully added";
    }

    public String addMovieDirectorPair(String directorName, String movieName) {
        if(!movieMap.containsKey(movieName) || !directorMap.containsKey(directorName)){
            return "Not Found";
        }
        movieMap.put(movieName, movieMap.get(movieName));
        directorMap.put(directorName, directorMap.get(directorName));
        List<String> currentMovies = new ArrayList<String>();
        if(movieDirectorMap.containsKey(directorName)) currentMovies = movieDirectorMap.get(directorName);
        currentMovies.add(movieName);
        movieDirectorMap.put(directorName, currentMovies);
        return "Successfully paired!!";
    }

    public Movie getMovieByName(String movieName) {
        if(movieMap.containsKey(movieName)) {
            return movieMap.get(movieName);
        }
        return null;
    }

    public Director getDirectorByName(String directorName) {
        if(directorMap.containsKey(directorName)) return directorMap.get(directorName);
        return null;
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> directorMovieList = new ArrayList<>();
        for(String movie: movieDirectorMap.keySet()){
            if(movieDirectorMap.get(movie).equals(directorName)) directorMovieList.add(movie);
        }
        return directorMovieList;
    }

    public List<String> findAllMovies() {
        List<String> movieList = new ArrayList<>();
        for(String movie: movieMap.keySet()) movieList.add(movie);
        return movieList;
    }

    public String deleteDirectorByName(String directorName) {
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
            for(String movie : movieDirectorMap.keySet()){
                if(movieDirectorMap.get(movie).equals(directorName)){
                    movieMap.remove(movie);
                    movieDirectorMap.remove(movie);
                }
            }
            return "Mentioned director is deleted successfully";
        }
        return "Not found";
    }

    public String deleteAllDirectors() {
        directorMap.clear();
        for(String movie: movieDirectorMap.keySet()){
            movieMap.remove(movie);
        }
        movieDirectorMap.clear();
        return "All movies successfully deleted!!";
    }
}
