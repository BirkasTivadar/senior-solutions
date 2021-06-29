package training.microservice.movies;

import lombok.Data;

@Data
public class MovieDto {

    private Long id;

    private String title;

    private double length;

    private double averageRate;
}
