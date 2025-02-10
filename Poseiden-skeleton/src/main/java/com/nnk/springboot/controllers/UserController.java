package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * To access to the user/list page
     *
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    /**
     *
     * To access to the user/add page
     *
     */
    @GetMapping("/user/add")
    public String addUser(UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);
        return "user/add";
    }

    /**
     *
     * To create a new user
     *
     */
    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        if (!result.hasErrors()) {

            userService.doSave(userDTO);
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }

        model.addAttribute("user", userDTO);
        return "user/add";
    }

    /**
     *
     * To access to the user/update page about a user
     *
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Optional<User> user = userService.findById(id);
        if(user.isEmpty()){

            model.addAttribute("errorMessage","The chosen user is empty");
            model.addAttribute("users", userService.findAll());
            return "user/list";
        }

        model.addAttribute("user", userService.getUserDTO(user.get()));
        return "user/update";
    }

    /**
     *
     * To update a user
     *
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO updatedUser,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", updatedUser);
            return "user/update";
        }

        userService.doSave(updatedUser);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    /**
     *
     * To delete a user
     *
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        userService.doDelete(id);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
