package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@Controller
public class BidListController {
    // TODO: Inject Bid service
    @Autowired
    private BidListService bidListService;

    @Autowired
    private UserService userService;

    /**
     *
     * To access to the bidList/list page
     *
     */
    @GetMapping("/bidList/list")
    public String home(Model model){
        List<BidListDTO> bidsListDTO = bidListService.findAll();
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("bidsList", bidsListDTO);
        // TODO: call service find all bids to show to the view
        return "bidList/list";
    }

    /**
     *
     * To access to the bidList/add page
     *
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidListDTO bid,Model model) {
        //model.addAttribute("bidList",new BidList());
        model.addAttribute("bidList",bid);
        return "bidList/add";
    }

    /**
     *
     * To create a new bidList
     *
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListDTO bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            bidListService.doSave(bid);
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            //model.addAttribute("users", userRepository.findAll());
            model.addAttribute("bidLists", bidListService.findAll());
            return "redirect:/bidList/list";
        }

        model.addAttribute("bidList", bid);
        return "bidList/add";
    }

    /**
     *
     * To access to the bidList/update page about a bidList
     *
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        Optional<BidList> bidList = bidListService.findById(id);
        if(bidList.isEmpty()){
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("errorMessage","The chosen bid is empty");
            model.addAttribute("bidLists", bidListService.findAll());
            return "bidList/list";
        }

        model.addAttribute("bidList",bidListService.getBidListDTO(bidList.get()));
        return "bidList/update";
    }

    /**
     *
     * To update a bidList
     *
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@Valid @ModelAttribute("bidList") BidListDTO updatedBidList,
                            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            model.addAttribute("bidList", updatedBidList);
            return "bidList/update";
        }

        bidListService.doSave(updatedBidList);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("bidLists", bidListService.findAll());

        return "redirect:/bidList/list";
    }

    /**
     *
     * To delete a bidList
     *
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list

        bidListService.doDelete(id);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }
}
