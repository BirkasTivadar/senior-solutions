package cinema;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Movie> movies = new ArrayList<>();

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> getMovies(Optional<String> title) {
        return movies.stream()
                .filter(movie -> title.isEmpty() || movie.getTitle().equalsIgnoreCase(title.get()))
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    private Movie findMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found:" + id));
    }

    public MovieDTO getMovieById(Long id) {
        return modelMapper.map(findMovieById(id), MovieDTO.class);
    }

    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(), command.getTitle(), command.getDate(), command.getMaxSpaces());
        movies.add(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieDTO reservationById(Long id, CreateReservationCommand command) {
        Movie movie = findMovieById(id);
        movie.reservation(command.getNumberOfSpaces());
        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieDTO updateMovieDate(Long id, UpdateDateCommand command) {
        Movie movie = findMovieById(id);
        movie.setDate(command.getNewDateTime());
        return modelMapper.map(movie, MovieDTO.class);
    }

    public void deleteAllMovies() {
        idGenerator = new AtomicLong();
        movies.clear();
    }
}
