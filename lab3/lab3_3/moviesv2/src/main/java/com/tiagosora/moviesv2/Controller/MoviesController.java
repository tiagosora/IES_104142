package com.tiagosora.moviesv2.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tiagosora.moviesv2.Exception.ResourceNotFoundException;
import com.tiagosora.moviesv2.Model.Movie;
import com.tiagosora.moviesv2.Model.Quote;
import com.tiagosora.moviesv2.Service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class MoviesController{

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/quotes")
    public List<Quote> getAllQuotes() {
        return moviesService.getAllQuotes();
    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable(value = "id") Long quoteId) throws ResourceNotFoundException {
        Quote quote = moviesService.getQuoteById(quoteId);
        return ResponseEntity.ok().body(quote);
    }

    @PostMapping("/quotes")
    public Quote createQuote(@Valid @RequestBody Quote quote){
        return moviesService.createQuote(quote);
    }

    @GetMapping("/quotes/r") // Random Quote From Random Movie
    public ResponseEntity<Quote> getQuoteRandomly() throws ResourceNotFoundException {
        Quote quote = moviesService.getRandomQuoteRandomMovie();
        return ResponseEntity.ok().body(quote);
    }

    @GetMapping("/movies/{id}/quotes/r") // Random Quote From Selected Movie
    public ResponseEntity<Quote> getQuoteRandomlyForMovie(@PathVariable(value = "id") Long movieId) throws ResourceNotFoundException {
        Quote quote = moviesService.getRandomQuoteByMovieId(movieId);
        return ResponseEntity.ok().body(quote);
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable(value = "id") Long quoteId, @Valid @RequestBody Quote quoteDetails) throws ResourceNotFoundException {
        Quote quote = moviesService.getQuoteById(quoteId);
        quote.setMovie(quoteDetails.getMovie());
        quote.setQuote(quoteDetails.getQuote());
        final Quote updatedQuote = moviesService.updateQuote(quote);
        return ResponseEntity.ok(updatedQuote);
    }

    @DeleteMapping("/quotes/{id}")
    public HashMap<String, Boolean> deleteQuote(@PathVariable(value = "id") Long quoteId) throws ResourceNotFoundException {
        moviesService.getQuoteById(quoteId);
        moviesService.deleteQuote(quoteId);
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // Separar as águas

    @GetMapping("/movies")
    public List<Movie> getAllShows() {
        return moviesService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movieId) throws ResourceNotFoundException {
        Movie movie = moviesService.getMovieById(movieId);
        return ResponseEntity.ok().body(movie);
    }

    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return moviesService.createMovie(movie);
    }

    @PutMapping("/movies/{id}") //Movie resource is updated
    public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movieId, @Valid @RequestBody Movie movieDetails) throws ResourceNotFoundException {
        Movie movie = moviesService.getMovieById(movieId);
        movie.setTitle(movieDetails.getTitle());
        final Movie updatedMovie = moviesService.updateMovie(movie);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/movies/{id}") //Movie resource is deleted
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long movieId) throws ResourceNotFoundException {
        moviesService.getMovieById(movieId);
        moviesService.deleteMovie(movieId);
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}