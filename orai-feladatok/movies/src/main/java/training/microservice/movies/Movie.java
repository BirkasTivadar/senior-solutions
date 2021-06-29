package training.microservice.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;

    private String title;

    private double length;

   private List<Integer> ratings;

   private double averageRate;

    public Movie(String title, double length) {
        this.title = title;
        this.length = length;
    }
}
