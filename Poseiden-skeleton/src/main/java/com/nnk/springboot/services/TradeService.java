package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public Trade doSave(Trade trade){
        return tradeRepository.save(trade);
    }

    public List<Trade> findAll(){
        return tradeRepository.findAll();
    }

    public Trade findById(Integer id){
        Optional<Trade> trade = tradeRepository.findById(id);
        return trade.get();
    }

    public void doDelete(Trade trade){
        tradeRepository.delete(trade);
    }
}
