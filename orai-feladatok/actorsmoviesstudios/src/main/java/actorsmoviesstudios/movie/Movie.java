package actorsmoviesstudios.movie;

//import actorsmoviesstudios.studio.Studio;
import actorsmoviesstudios.actor.Actor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "movies")
//public class Movie {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "movie_title")
//    private String title;
//
//    @Column(name = "premiere_year")
//    private int year;
//
//    @ManyToOne
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @JsonBackReference
//    private Studio studio;
//
//    @ManyToMany()
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonBackReference
//    private Set<Actor> actors;
//
//    public Movie(String title, int year, Studio studio) {
//        this.title = title;
//        this.year = year;
//        this.studio = studio;
//    }
//
//    public void addActor(Actor actor) {
//        if (actors == null) {
//            actors = new HashSet<>();
//        }
//        actors.add(actor);
//        actor.getMovies().add(this);
//    }
//}
