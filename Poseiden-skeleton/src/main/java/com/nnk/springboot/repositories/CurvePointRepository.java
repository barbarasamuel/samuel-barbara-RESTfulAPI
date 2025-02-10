package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * To manipulate data from the database linked to the curvepoint table
 *
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
