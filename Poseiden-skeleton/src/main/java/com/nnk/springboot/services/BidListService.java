package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public BidList doSave(BidList bidList){
        return bidListRepository.save(bidList);
    }

    public List<BidList> findAll(){
        return bidListRepository.findAll();
    }

    public BidList findById(Integer id){
        Optional<BidList> bidList = bidListRepository.findById(id);
        return bidList.get();
    }

    public void doDelete(BidList bidList){
        bidListRepository.delete(bidList);
    }
}
