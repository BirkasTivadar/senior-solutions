package com.training360.moviesjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Long> {
}
