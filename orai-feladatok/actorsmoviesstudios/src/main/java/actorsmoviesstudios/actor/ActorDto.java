package actorsmoviesstudios.actor;
//
//import actorsmoviesstudios.movie.Movie;
//import actorsmoviesstudios.movie.MovieDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {

    private Long id;

    private String name;

//    private LocalDate birthOfDate;

//    private List<Movie> movies;

}
