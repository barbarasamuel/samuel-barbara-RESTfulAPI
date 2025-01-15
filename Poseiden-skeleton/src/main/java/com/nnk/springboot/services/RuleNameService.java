package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public RuleName doSave(RuleName ruleName){
        return ruleNameRepository.save(ruleName);
    }

    public List<RuleName> findAll(){
        return ruleNameRepository.findAll();
    }

    public RuleName findById(Integer id){
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        return ruleName.get();
    }

    public void doDelete(RuleName ruleName){
        ruleNameRepository.delete(ruleName);
    }
}
