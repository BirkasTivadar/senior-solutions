package com.training360.moviesjpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMovieRatingCommand {

    private Integer rating;
}
