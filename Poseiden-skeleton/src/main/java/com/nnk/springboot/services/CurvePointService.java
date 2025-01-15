package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePoint doSave(CurvePoint curvePoint){
        return curvePointRepository.save(curvePoint);
    }

    public List<CurvePoint> findAll(){
        return curvePointRepository.findAll();
    }

    public CurvePoint findById(Integer id){
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        return curvePoint.get();
    }

    public void doDelete(CurvePoint curvePoint){
        curvePointRepository.delete(curvePoint);
    }
}
