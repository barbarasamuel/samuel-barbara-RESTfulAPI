package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * To manipulate data from the database linked to the rulename table
 *
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

}
