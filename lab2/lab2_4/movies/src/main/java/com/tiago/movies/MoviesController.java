package com.tiago.movies;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MoviesController {


    public static MoviesAPI moviesAPI = new MoviesAPI();

    @GetMapping("/api/quote")
    public Map<String, String> quote() {
        String[] data = moviesAPI.randomQuoteRandomMovie();
        Map<String, String> output = new LinkedHashMap<>();
        output.put("id", data[0]);
        output.put("show", data[1]);
        output.put("quote", data[2]);
        return output;
    }

    @GetMapping("/api/shows")
    public List<Map<String, String>> show(){
        List<Map<String, String>> output= new ArrayList<>();
        for(Movie movie : moviesAPI.getMoviesInAPI())
        {
            Map<String,String> data = new LinkedHashMap<>();
            data.put("id", movie.getIdString());
            data.put("show", movie.getTitle());
            output.add(data);
        }
        return output;
    }

    @GetMapping("/api/quotes")
    public Map<String,String> quote(@RequestParam(value = "show", defaultValue = "0") String movieID) {
        int id = Integer.parseInt(movieID);
        // Available ids = [1-3]
        if (id < 1 || id > 3){
            return null;
        }
        String[] data = moviesAPI.randomQuoteMovieId(id - 1);
        Map<String, String> output = new LinkedHashMap<>();
        output.put("id", data[0]);
        output.put("show", data[1]);
        output.put("quote", data[2]);
        return output;
    }
}