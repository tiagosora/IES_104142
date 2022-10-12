package com.tiago.movies;

import java.util.ArrayList;

public class Movie {
    private int id;
    private String title;
    private ArrayList<String> quotes;
    public Movie(int id) {
        this.id = id;
        this.quotes = new ArrayList<>();
    }
    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
        this.quotes = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void addQuote(String quote) {
        quotes.add(quote);
    }
    public String getIdString() {
        return Integer.toString(this.id);
    }
    public String getTitle() {
        if (this.title == null) {
            return "Movie Not Defined";
        }
        return this.title;
    }
    public ArrayList<String> getQuotes() {
        return quotes;
    }
}
