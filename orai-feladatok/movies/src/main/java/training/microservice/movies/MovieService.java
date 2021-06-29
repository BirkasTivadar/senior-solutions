package training.microservice.movies;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private ModelMapper modelMapper;

    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>(List.of(
            new Movie("Titanic", 2.3)
    )));

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> movieList(Optional<String> title) {
        Type targetListType = new TypeToken<List<Movie>>() {
        }.getType();
        List<Movie> filtered = movies.stream().filter(m -> title.isEmpty() ||
                m.getTitle().toLowerCase().equals(title.get().toLowerCase())).collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }
}
