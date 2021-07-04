package training.microservice.movies;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> movieList(@RequestParam Optional<String> title) {
        return movieService.movieList(title);
    }


    @GetMapping("{id}")
    public MovieDto findMovieById(@PathVariable("id") Long id) {
        return movieService.findMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @PostMapping("{id}/rating")
    public MovieDto updateMovieRating(@PathVariable("id") Long id, @RequestBody NewMovieRatingCommand command) {
        return movieService.addRating(id, command);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
    }
}

