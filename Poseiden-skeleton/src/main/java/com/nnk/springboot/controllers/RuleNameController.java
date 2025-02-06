package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {
    // TODO: Inject RuleName service
    @Autowired
    private RuleNameService ruleNameService;

    @Autowired
    private UserService userService;

    /**
     *
     * To access to the ruleName/list page
     *
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        List<RuleName> ruleNameList = ruleNameService.findAll();
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("ruleNames", ruleNameList);
        return "ruleName/list";
    }

    /**
     *
     * To access to the ruleName/add page
     *
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName, Model model) {
        model.addAttribute("ruleName",ruleName);
        return "ruleName/add";
    }

    /**
     *
     * To create a new ruleName
     *
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.doSave(ruleName);
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("ruleNames", ruleNameService.findAll());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    /**
     *
     * To access to the ruleName/update page about a ruleName
     *
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        Optional<RuleName> ruleName = ruleNameService.findById(id);
        if(ruleName.isEmpty()){
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("errorMessage","The chosen rule name is empty");
            model.addAttribute("ruleNames", ruleNameService.findAll());
            return "ruleName/list";
        }

        model.addAttribute("ruleName",ruleName.get());
        return "ruleName/update";
    }

    /**
     *
     * To update a ruleName
     *
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@Valid @ModelAttribute("ruleName") RuleName updatedRuleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleNameService.doSave(updatedRuleName);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }

    /**
     *
     * To delete a ruleName
     *
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        //RuleName ruleName = ruleNameService.findById(id);
        ruleNameService.doDelete(id);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }
}
