package com.training360.moviesjpa;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
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

    private MoviesRepository repository;

//    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>());

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> movieList() {
        return repository.findAll().stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    private Movie getMovieById(Long id) {
        return modelMapper.map()
    }

    public MovieDto findMovieById(Long id) {
        return modelMapper.map(getMovieById(id), MovieDto.class);
    }


    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(), command.getTitle());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    public MovieDto addRating(Long id, NewMovieRatingCommand command) {
        Movie movie = getMovieById(id);
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);
    }

    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movies.remove(movie);
    }

    public void deleteAllMovies() {
        idGenerator = new AtomicLong();
        movies.clear();
    }
}
