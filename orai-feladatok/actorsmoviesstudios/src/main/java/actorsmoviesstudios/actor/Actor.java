package actorsmoviesstudios.actor;


//import actorsmoviesstudios.movie.Movie;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actor_name")
    private String name;
//
//    @Column(name = "date_of_birth")
//    private LocalDate birthOfDate;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "actors")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<Movie> movies;

    public Actor(String name, LocalDate birthOfDate) {
        this.name = name;
//        this.birthOfDate = birthOfDate;
    }

//    public void addMovie(Movie movie){
//        if(movies == null){
//            movies = new ArrayList<>();
//        }
//        movies.add(movie);
//        movie.getActors().add(this);
//    }
}
