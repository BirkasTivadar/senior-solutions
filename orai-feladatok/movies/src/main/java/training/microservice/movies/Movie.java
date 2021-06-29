package training.microservice.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;

    private String title;

    private double length;

    private List<Integer> ratings = new ArrayList<>();

    private double averageRate;

    public Movie(String title, double length) {
        this.title = title;
        this.length = length;
    }

    public void addRate(int rate) {
        ratings.add(rate);
        averageRate = ratings.stream().mapToInt(r->r).sum() / ratings.size();
    }
}
