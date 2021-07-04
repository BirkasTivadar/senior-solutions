package training.microservice.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoviesControllerRestTemplateIT {

    private static final String DEFAULT_URL = "/api/movies";

    @Autowired
    TestRestTemplate template;

    @Autowired
    MovieService movieService;

    @BeforeEach
    void init() {
        movieService.deleteAllMovies();
    }

    @Test
    void testMovieList() {
        MovieDto movieDto =
                template.postForObject(DEFAULT_URL, new CreateMovieCommand("Titanic", 2.56), MovieDto.class);

        assertEquals("Titanic", movieDto.getTitle());

        template.postForObject(DEFAULT_URL, new CreateMovieCommand("Batman", 1.78), MovieDto.class);

        List<MovieDto> locations =
                template.exchange(DEFAULT_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MovieDto>>() {
                        })
                        .getBody();

        assertThat(locations)
                .extracting(MovieDto::getTitle)
                .containsExactly("Titanic", "Batman");
    }
}
