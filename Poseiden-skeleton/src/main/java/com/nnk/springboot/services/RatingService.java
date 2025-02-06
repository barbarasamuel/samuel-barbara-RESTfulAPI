package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    /**
     *
     * To save or update a rating
     *
     */
    public Rating doSave(Rating rating){
        return ratingRepository.save(rating);
    }

    /**
     *
     * To get the list of rating
     *
     */
    public List<Rating> findAll(){
        return ratingRepository.findAll();
    }

    /**
     *
     * To get a rating
     *
     */
    public Optional<Rating> findById(Integer id){
        return ratingRepository.findById(id);
    }

    /**
     *
     * To delete a rating
     *
     */
    public void doDelete(Integer id){
        ratingRepository.deleteById(id);
    }
}
