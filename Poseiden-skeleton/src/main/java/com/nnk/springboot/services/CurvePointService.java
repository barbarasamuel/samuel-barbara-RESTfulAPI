package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePoint doSave(CurvePointDTO curvePointDTO){
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(curvePointDTO.getId());
        curvePoint.setCurveId(curvePointDTO.getCurveId());
        curvePoint.setTerm(curvePoint.getTermAsDouble(curvePointDTO.getTerm()));
        curvePoint.setValue(curvePoint.getValueAsDouble(curvePointDTO.getValue()));
        return curvePointRepository.save(curvePoint);
    }

    public List<CurvePointDTO> findAll(){
        List<CurvePointDTO> curvePointDTOList = new ArrayList<>();
        List<CurvePoint> curvePointList = curvePointRepository.findAll();

        for (CurvePoint curseCurvePoint : curvePointList
        ) {
            curvePointDTOList.add(
                    new CurvePointDTO(
                            curseCurvePoint.getId(),
                            curseCurvePoint.getCurveId(),
                            curseCurvePoint.getTermAsString(curseCurvePoint.getTerm()),
                            curseCurvePoint.getValueAsString(curseCurvePoint.getValue()))
            );
        }
        return curvePointDTOList;
    }

    public Optional<CurvePoint> findById(Integer id){
        return curvePointRepository.findById(id);
    }

    public void doDelete(Integer id){
        curvePointRepository.deleteById(id);
    }

    public CurvePointDTO getCurvePointDTO(CurvePoint curvePoint){
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(curvePoint.getId());
        curvePointDTO.setCurveId(curvePoint.getCurveId());
        curvePointDTO.setTerm(curvePoint.getTermAsString(curvePoint.getTerm()));
        curvePointDTO.setValue(curvePoint.getValueAsString(curvePoint.getValue()));

        return curvePointDTO;
    }
}
