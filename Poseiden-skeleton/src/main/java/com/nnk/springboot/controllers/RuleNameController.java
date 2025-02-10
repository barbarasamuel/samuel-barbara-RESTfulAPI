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


@Controller
public class RuleNameController {

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

        ruleNameService.doDelete(id);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }
}
