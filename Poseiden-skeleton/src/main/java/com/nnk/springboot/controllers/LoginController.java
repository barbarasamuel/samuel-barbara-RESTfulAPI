package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
//@RequestMapping("app")
public class LoginController {

    /*@Autowired
    private UserRepository userRepository;*/
    @Autowired
    private UserService userService;


    /**
     *
     * To access to the private web pages
     *
     */
    @PostMapping("/access")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request){

        try{

            User user = userService.getUser(username);
            if(userService.checkPassword(user,password)) {
                request.login(user.getUsername(), user.getPassword());

                log.info("Login successful");
                return "redirect:/bidList/list";
            }

            return "redirect:/login?error";

        }catch (Exception e){
            log.error("Login failed");
            return "redirect:/login?error";
        }
    }

    /**
     *
     * To access to the login page
     *
     */
    @GetMapping("/login")
    public String login(User user, Model model) {

        model.addAttribute("user", user);
        return "login";
    }
    /*public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");

        return mav;
    }*/

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        /*mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");*/
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

    /**
     *
     * To log out
     *
     */
    @GetMapping("/logout")
    public String logout(){
        log.info("Logout successful");
        return "redirect:/login?logout";
    }
}
