package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.Optional;

/**
 *
 * To manipulate data from the database linked to the users table
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{//}, JpaSpecificationExecutor<User> {
    public Optional<User> findById(Integer id);
    public User findByUsername(String username);
}
