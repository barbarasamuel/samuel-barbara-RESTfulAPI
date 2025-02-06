package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * To manipulate data from the database linked to the curvepoint table
 *
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {
    public Optional<CurvePoint> findById(Integer id);
}
