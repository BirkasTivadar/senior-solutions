package training.microservice.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;

    private String title;

    private Double length;

    private List<Integer> ratings = new ArrayList<>();

    private Double averageRating;

    public Movie(Long id, String title, double length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRating(int rating) {
        ratings.add(rating);
        averageRating = ratings.stream().mapToInt(r -> r).summaryStatistics().getAverage();
    }
}
