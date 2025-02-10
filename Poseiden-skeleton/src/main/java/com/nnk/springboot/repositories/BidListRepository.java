package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * To manipulate data from the database linked to the bidlist table
 *
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
