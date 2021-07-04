package training.microservice.movies;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>());

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> movieList(Optional<String> title) {
        return movies.stream()
                .filter(movie -> title.isEmpty() || movie.getTitle().equalsIgnoreCase(title.get()))
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    private Movie getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + id));
    }

    public MovieDto findMovieById(Long id) {
        return modelMapper.map(getMovieById(id), MovieDto.class);
    }


    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(), command.getTitle(), command.getLength());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movies.remove(movie);
    }

    public MovieDto addRating(Long id, NewMovieRatingCommand command) {
        Movie movie = getMovieById(id);
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);
    }
}
