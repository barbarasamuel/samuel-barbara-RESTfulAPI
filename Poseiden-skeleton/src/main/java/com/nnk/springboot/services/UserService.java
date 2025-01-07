package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User doSave(User user){
        return userRepository.save(user);
    }

    public User findById(Integer id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.get();
    }

    public void doDelete(User user){
        userRepository.delete(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
