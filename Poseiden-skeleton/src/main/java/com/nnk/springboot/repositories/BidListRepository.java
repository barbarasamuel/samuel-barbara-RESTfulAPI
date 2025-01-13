package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface BidListRepository extends JpaRepository<BidList, Integer>, JpaSpecificationExecutor<BidList> {
    public Optional<BidList> findById(Integer id);
}
