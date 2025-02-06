package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * To manipulate data from the database linked to the trade table
 *
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
