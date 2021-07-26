package com.training360.moviesjpa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> movieDtoList() {
        return movieService.movieList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto createMovie(@RequestBody CreateMovieCommand command){
        return movieService.createMovie(command);
    }

    @PostMapping("{id}/rating")
    public MovieDto updateMovieRating(@PathVariable("id") Long id, @RequestBody NewMovieRatingCommand command) {
        return movieService.addRating(id, command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        movieService.deleteAllMovies();
    }

}
