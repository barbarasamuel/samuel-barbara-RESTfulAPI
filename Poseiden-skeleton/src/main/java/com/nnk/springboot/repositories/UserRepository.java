package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 *
 * To manipulate data from the database linked to the users table
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{//}, JpaSpecificationExecutor<User> {

    User findByUsername(String username);
}
