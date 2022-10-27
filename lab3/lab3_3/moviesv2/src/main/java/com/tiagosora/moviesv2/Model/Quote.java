package com.tiagosora.moviesv2.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quotes")
public class Quote {

    private long id;
    private Movie movie;
    private String quote;

    public Quote(){}

    public Quote(Movie movie, String quote){
        this.movie = movie;
        this.quote = quote;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // @Column(name = "movie", nullable = false)
    @ManyToOne
    public Movie getMovie() {
        return this.movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Column(name = "quote", nullable = false)
    public String getQuote() {
        return this.quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Quote [Id=" + id + ", Movie=" + this.movie.getTitle() + ", Quote=" + this.quote + "]";
    }
}
