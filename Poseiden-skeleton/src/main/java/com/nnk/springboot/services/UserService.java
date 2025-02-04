package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User doSave(UserDTO gottenUser){
        User user = new User();
        user.setId(gottenUser.getId());
        user.setFullname(gottenUser.getFullname());
        user.setUsername(gottenUser.getUsername());
        user.setPassword(passwordEncoder.encode((gottenUser.getPassword())));
        user.setRole(gottenUser.getRole());

        return userRepository.save(user);
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public void doDelete(Integer id){
        userRepository.deleteById(id);
    }

    public List<UserDTO> findAll(){
        List<UserDTO> usersDTOList = new ArrayList<>();
        List<User> usersList = userRepository.findAll();

        for(User user: usersList){
            usersDTOList.add(new UserDTO(
               user.getId(),
               user.getFullname(),
               user.getUsername(),
               user.getPassword(),
               user.getRole()
            ));
        }

        return usersDTOList;
    }

    public User getUser(String username){

        return userRepository.findByUsername(username);
    }

    public Boolean checkPassword(User user, String username, String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(password, user.getPassword())) {
            return true;
        }

        return false;
    }

    public User getFullname(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userRepository.findByUsername(userDetails.getUsername());
    }

    public UserDTO getUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFullname(user.getFullname());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());

        return userDTO;
    }
}
