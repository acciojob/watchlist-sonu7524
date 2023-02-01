package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String response = movieService.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        String response = movieService.addDirector(director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String directorName,@RequestParam String movieName){
        String response = movieService.addMovieDirectorPair(directorName,movieName);
        if(response.equals("Not Found")){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public Movie getMovieByName(@PathVariable("name") String movieName){
        return movieService.getMovieByName(movieName);
    }
    @GetMapping("/movies/get-director-by-name/{name}")
    public Director getDirectorByName(@PathVariable("name") String directorName){
        return movieService.getDirectorByName(directorName);
    }

    @GetMapping("/movies/get-movies-by-director-name/{directorName}")
    public List<String> getMoviesByDirectorName(@PathVariable("directorName") String directorName){
        return movieService.getMoviesByDirectorName(directorName);
    }
    @GetMapping("/movies/get-all-movies")
    public List<String> findAllMovies(){
        return movieService.findAllMovies();
    }
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
        String response = movieService.deleteDirectorByName(directorName);
        if(response.equals("Invalid Name")) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        String response = movieService.deleteAllDirectors();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
