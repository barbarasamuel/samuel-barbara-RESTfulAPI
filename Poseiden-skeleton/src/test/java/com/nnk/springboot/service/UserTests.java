package com.nnk.springboot.service;

import com.nnk.springboot.PoseidenApplication;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PoseidenApplication.class})
public class UserTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void userTest() {
        User user = new User();
        user.setFullname("Camille");
        user.setUsername("camile");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRole("USER");

        // Save
        user = userRepository.save(user);
        assertNotNull(user.getId());
        assertTrue(user.getUsername().equals("camile"));

        // Update
        user.setUsername("camille");
        user = userRepository.save(user);
        assertTrue(user.getUsername().equals("camille"));

        // Find
        List<User> listResult = userRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = user.getId();
        userRepository.delete(user);
        Optional<User> userList = userRepository.findById(id);
        assertFalse(userList.isPresent());
    }
}
