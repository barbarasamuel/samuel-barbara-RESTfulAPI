package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * To manipulate data from the database linked to the rating table
 *
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
