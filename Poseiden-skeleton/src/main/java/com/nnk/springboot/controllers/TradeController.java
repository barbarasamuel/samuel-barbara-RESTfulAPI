package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {
    // TODO: Inject Trade service
    @Autowired
    private TradeService tradeService;

    @Autowired
    private UserService userService;

    @GetMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        List<TradeDTO> tradeList = tradeService.findAll();
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("trades", tradeList);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(TradeDTO trade, Model model) {
        model.addAttribute("trade",trade);
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") TradeDTO trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        if (!result.hasErrors()) {
            tradeService.doSave(trade);
            model.addAttribute("trades", tradeService.findAll());
            return "redirect:/trade/list";
        }

        model.addAttribute("trade", trade);
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        Optional<Trade> trade = tradeService.findById(id);
        if(trade.isEmpty()){
            model.addAttribute("errorMessage","The chosen trade is empty");
            model.addAttribute("trades", tradeService.findAll());
            return "trade/list";
        }
        model.addAttribute("trade",tradeService.getTradeDTO(trade.get()));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@Valid @ModelAttribute("trade") TradeDTO updatedTrade,
                              BindingResult result,  Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            model.addAttribute("trade", updatedTrade);
            return "trade/update";
        }

        tradeService.doSave(updatedTrade);
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        tradeService.doDelete(id);
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";
    }
}
