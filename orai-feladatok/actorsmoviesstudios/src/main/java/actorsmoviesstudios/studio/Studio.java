package actorsmoviesstudios.studio;


//import actorsmoviesstudios.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "studios")
//public class Studio {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "studio_name")
//    private String name;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = ("studio"))
//    List<Movie> movies;
//
//    public Studio(String name) {
//        this.name = name;
//    }
//
//    public void addMovie(Movie movie) {
//        if (movies == null) {
//            movies = new ArrayList<>();
//        }
//        movies.add(movie);
//        movie.setStudio(this);
//    }
//}
