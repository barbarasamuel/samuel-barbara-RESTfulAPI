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

    /**
     *
     * To save or update a ruleName
     *
     */
    public RuleName doSave(RuleName ruleName){
        return ruleNameRepository.save(ruleName);
    }

    /**
     *
     * To get the list of ruleName
     *
     */
    public List<RuleName> findAll(){
        return ruleNameRepository.findAll();
    }

    /**
     *
     * To get a ruleName
     *
     */
    public Optional<RuleName> findById(Integer id){
        return ruleNameRepository.findById(id);
    }

    /**
     *
     * To delete a ruleName
     *
     */
    public void doDelete(Integer id){
        ruleNameRepository.deleteById(id);
    }
}
