package com.tiagosora.moviesv2.Service;

import com.tiagosora.moviesv2.Exception.ResourceNotFoundException;
import com.tiagosora.moviesv2.Model.Movie;
import com.tiagosora.moviesv2.Model.Quote;
import com.tiagosora.moviesv2.Repository.MovieRepository;
import com.tiagosora.moviesv2.Repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoviesService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private QuoteRepository quoteRepository;



    public void deleteQuote(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }

    public Quote updateQuote(Quote quote)  {
        return quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getRandomQuoteByMovieId(Long movieId) throws ResourceNotFoundException {
        List<Long> quotes= new ArrayList<>();
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("ERROR: Movie not found for this id :: " + movieId));
        for(Quote q : quoteRepository.findAll()) {
            if(q.getMovie().getTitle().equals(movie.getTitle())) {
                quotes.add(q.getId());
            }
        }
        Random random = new Random();
        return quoteRepository.findById(quotes.get(random.nextInt(quotes.size()))).orElse(null);
    }

    public Quote getQuoteById(Long quoteId) throws ResourceNotFoundException {
        return quoteRepository.findById(quoteId).orElseThrow(() -> new ResourceNotFoundException("ERROR: Quote not found for this id :: " + quoteId));
    }

    public Quote getRandomQuoteRandomMovie() throws ResourceNotFoundException {
        ArrayList<Long> ids = new ArrayList<>();
        for(Quote q : quoteRepository.findAll())  {
            ids.add(q.getId());
        }
        Random random = new Random();
        return quoteRepository.findById(ids.get(random.nextInt(ids.size()))).orElseThrow(() -> new ResourceNotFoundException("ERROR: No Quotes In The API"));
    }

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    // Separar as águas

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovieById(Long movieId) throws ResourceNotFoundException {
        return movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("ERROR: Movie not found for this id :: " + movieId));
    }

    public Movie updateMovie(Movie movie)  {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}
