package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
//@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersistentTokenBasedRememberMeServices rememberMeServices;

    /**
     *
     * To access to the private web pages
     *
     */
    @PostMapping("/access")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(name="remember-me",defaultValue="false") boolean rememberMe,
            HttpServletRequest request,
            HttpServletResponse response){

        try{
            request.login(username,password);
            if(rememberMe){
                rememberMeServices.loginSuccess(request,response, (Authentication) request.getUserPrincipal());
            }
            log.info("Login successful");
            return "redirect:/";
        }catch (Exception e){
            log.error("Login failed");
            return "redirect:/login?error";
        }
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");

        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
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
}
