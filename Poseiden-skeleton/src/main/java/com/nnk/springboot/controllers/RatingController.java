package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//import javax.validation.Valid;

@Controller
public class RatingController {
    // TODO: Inject Rating service
    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    /**
     *
     * To access to the rating/list page
     *
     */
    @GetMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        List<Rating> ratingList = ratingService.findAll();
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("ratings", ratingList);
        return "rating/list";
    }

    /**
     *
     * To access to the rating/add page
     *
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating, Model model){
        model.addAttribute("rating",rating);
        return "rating/add";
    }

    /**
     *
     * To create a new rating
     *
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        if (!result.hasErrors()) {
            ratingService.doSave(rating);
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("ratings", ratingService.findAll());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    /**
     *
     * To access to the rating/update page about a rating
     *
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        Optional<Rating> rating = ratingService.findById(id);
        if(rating.isEmpty()){
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("errorMessage","The chosen rating is empty");
            model.addAttribute("ratings", ratingService.findAll());
            return "rating/list";
        }

        model.addAttribute("rating",rating.get());
        return "rating/update";
    }

    /**
     *
     * To update a rating
     *
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@Valid @ModelAttribute("rating") Rating updatedRating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            return "rating/update";
        }

        ratingService.doSave(updatedRating);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }

    /**
     *
     * To delete a rating
     *
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list

        ratingService.doDelete(id);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }
}
