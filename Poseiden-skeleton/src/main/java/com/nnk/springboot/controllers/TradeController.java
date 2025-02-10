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


@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private UserService userService;

    /**
     *
     * To access to the trade/list page
     *
     */
    @GetMapping("/trade/list")
    public String home(Model model)
    {

        List<TradeDTO> tradeList = tradeService.findAll();
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("trades", tradeList);
        return "trade/list";
    }

    /**
     *
     * To access to the trade/add page
     *
     */
    @GetMapping("/trade/add")
    public String addUser(TradeDTO trade, Model model) {
        model.addAttribute("trade",trade);
        return "trade/add";
    }

    /**
     *
     * To create a new trade
     *
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") TradeDTO trade, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            tradeService.doSave(trade);
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("trades", tradeService.findAll());
            return "redirect:/trade/list";
        }

        model.addAttribute("trade", trade);
        return "trade/add";
    }

    /**
     *
     * To access to the trade/update page about a trade
     *
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Optional<Trade> trade = tradeService.findById(id);
        if(trade.isEmpty()){
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("errorMessage","The chosen trade is empty");
            model.addAttribute("trades", tradeService.findAll());
            return "trade/list";
        }
        model.addAttribute("trade",tradeService.getTradeDTO(trade.get()));
        return "trade/update";
    }

    /**
     *
     * To update a trade
     *
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@Valid @ModelAttribute("trade") TradeDTO updatedTrade,
                              BindingResult result,  Model model) {

        if (result.hasErrors()) {
            model.addAttribute("trade", updatedTrade);
            return "trade/update";
        }

        tradeService.doSave(updatedTrade);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";
    }

    /**
     *
     * To delete a trade
     *
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        tradeService.doDelete(id);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";
    }
}
