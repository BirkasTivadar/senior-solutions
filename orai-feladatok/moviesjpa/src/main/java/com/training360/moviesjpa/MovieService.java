package com.training360.moviesjpa;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private ModelMapper modelMapper;

//    private AtomicLong idGenerator = new AtomicLong();

    private MoviesRepository repository;

//    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>());

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> movieList() {
        return repository.findAll().stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .toList();
    }


    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        repository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Transactional
    public MovieDto addRating(Long id, NewMovieRatingCommand command) {
        Movie movie = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find movie by id: " + id));
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDto.class);
    }

    public void deleteAllMovies() {
        repository.deleteAll();
    }
}
