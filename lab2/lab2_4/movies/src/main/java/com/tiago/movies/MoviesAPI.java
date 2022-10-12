package com.tiago.movies;

import java.util.ArrayList;
import java.util.Random;

public class MoviesAPI {
    private ArrayList<Movie> moviesInAPI;
    public MoviesAPI(){
        this.moviesInAPI = new ArrayList<>();
        // Movie 1 - Harry Potter
        Movie movie1 = new Movie(1, "Harry Potter");
        movie1.addQuote("There are some things you can’t share without ending up liking each other, and knocking out a twelve-foot mountain troll is one of them.");
        movie1.addQuote("It takes a great deal of bravery to stand up to our enemies, but just as much to stand up to our friends.");
        movie1.addQuote("I am what I am, an’ I’m not ashamed. ‘Never be ashamed,’ my ol’ dad used ter say, ‘there’s some who’ll hold it against you, but they’re not worth botherin’ with.");
        movie1.addQuote("No good sittin’ worryin’ abou’ it. What’s comin’ will come, an’ we’ll meet it when it does.");
        movie1.addQuote("Family…Whatever yeh say, blood’s important. . . .");
        this.moviesInAPI.add(movie1);

        // Movie 2 - *Untitled*
        Movie movie2 = new Movie(2);
        movie2.addQuote("I don't hire people who have to be told to be nice. I hire nice people.");
        movie2.addQuote("No human thing is of serious importance.");
        movie2.addQuote("It is bad luck to be superstitious.");
        movie2.addQuote("Among those whom I like or admire, I can find no common denominator, but among those whom I love, I can: all of them make me laugh.");
        movie2.addQuote("When you can't have what you want, it's time to start wanting what you have.");
        this.moviesInAPI.add(movie2);

        // Movie 3 - Lord of The Rings
        Movie movie3 = new Movie(3, "Lord of The Rings");
        movie3.addQuote("Not all those who wander are lost.");
        movie3.addQuote("All’s well that ends better.");
        movie3.addQuote("Deeds will not be less valiant because they are unpraised.");
        movie3.addQuote("Even the smallest person can change the course of the future.");
        movie3.addQuote("You step into the Road, and if you don’t keep your feet, there is no knowing where you might be swept off to.");
        movie3.addQuote("Let him not vow to walk in the dark, who has not seen the nightfall.");
        this.moviesInAPI.add(movie3);
    }
    public int randomMovieId() {
        Random random = new Random();
        return (random.nextInt(0 , moviesInAPI.size()));
    }
    public Movie randomMovie() {
        return this.moviesInAPI.get(randomMovieId());
    }
    public String[] randomQuoteMovieId(int id) {
        if (id < moviesInAPI.size()) {
            Movie movie = moviesInAPI.get(id);
            ArrayList<String> quotes = movie.getQuotes();
            Random random = new Random();
            String quote = quotes.get(random.nextInt(quotes.size()));
            String[] data = {movie.getIdString(), movie.getTitle(), quote};
            return data;
        }
        return null;
    }
    public String[] randomQuoteRandomMovie() {
        Movie randomMovie = randomMovie();
        ArrayList<String> quotes = randomMovie.getQuotes();
        Random random = new Random();
        String quote = quotes.get(random.nextInt(quotes.size()));
        String[] data = {randomMovie.getIdString(), randomMovie.getTitle(), quote};
        return data;
    }

    public ArrayList<Movie> getMoviesInAPI() {
        return moviesInAPI;
    }
}
