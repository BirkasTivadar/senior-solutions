package training.microservice.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MoviesControllerWebMvcIT {

    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testMovieList() throws Exception {
        when(movieService.movieList(any()))
                .thenReturn(List.of(
                        new MovieDto("Titanic", 2.34, 0.0),
                        new MovieDto("Batman", 1.96, 0.0)
                ));

        mockMvc.perform(get("/api/movies"))
//                .andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", equalTo("Titanic")));
    }

    @Test
    void testFindMovieById() throws Exception {
        when(movieService.findMovieById(any()))
                .thenReturn(new MovieDto("Batman", 1.96, 3.5));

        mockMvc.perform(get("/api/movies/"))
                .andExpect(status().isOk())
                .equals(new MovieDto("Batman", 1.96, 3.5));
    }

    @Test
    void testCreateMovie() throws Exception {
        when(movieService.createMovie(any()))
                .thenReturn(new MovieDto("Batman", 1.96, 3.5));

        mockMvc.perform(get("/api/movies/"))
                .andExpect(status().isOk())
                .equals(new MovieDto("Batman", 1.96, 3.5));
    }


    @Test
    void testMovieRating() throws Exception {

        when(movieService.addRating(any(), any()))
                .thenReturn(new MovieDto("Batman", 1.34, 5.0));

        mockMvc.perform(get("/api/movies/"))
                .andExpect(status().isOk())
                .equals(new MovieDto("Batman", 1.34, 5.0));
    }
}
