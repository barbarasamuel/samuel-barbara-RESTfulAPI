package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            //userRepository.save(user);
            userService.doSave(userDTO);
            //model.addAttribute("users", userRepository.findAll());
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }

        model.addAttribute("user", userDTO);
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        Optional<User> user = userService.findById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        if(user.isEmpty()){
            model.addAttribute("errorMessage","The chosen user is empty");
            model.addAttribute("users", userService.findAll());
            return "user/list";
        }

        //user.setPassword("");
        model.addAttribute("user", userService.getUserDTO(user.get()));
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    /*public String updateUser(@PathVariable("id") Integer id, @RequestParam("") User user,
                             BindingResult result, Model model) {*/
    public String updateUser(@Valid @ModelAttribute("user") UserDTO updatedUser,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", updatedUser);
            return "user/update";
        }

        /*
        userRepository.save(user);*/

        userService.doSave(updatedUser);
        //model.addAttribute("users", userRepository.findAll());
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        /*User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());*/
        //User user = userService.findById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.doDelete(id);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
