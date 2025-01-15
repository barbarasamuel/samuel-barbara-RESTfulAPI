package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {
    public Optional<CurvePoint> findById(Integer id);
}
