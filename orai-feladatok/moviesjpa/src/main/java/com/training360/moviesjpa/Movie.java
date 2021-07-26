package com.training360.moviesjpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_title")
    private String title;

    @ElementCollection
    @CollectionTable(name = "ratings", joinColumns = @JoinColumn(name = "rating_id"))
    @Column(name = "rating")
    private List<Integer> ratings;

    public Movie(String title) {
        this.title = title;
        this.ratings = new ArrayList<>();
    }

    public void addRating(Integer rating) {
        ratings.add(rating);
    }
}
