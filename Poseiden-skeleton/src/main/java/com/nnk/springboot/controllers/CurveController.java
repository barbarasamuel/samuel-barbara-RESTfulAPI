package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service
    @Autowired
    private CurvePointService curvePointService;

    @Autowired
    private UserService userService;

    /**
     *
     * To access to the curvePoint/list page
     *
     */
    @GetMapping("/curvePoint/list")
    public String home(Model model)
    {

        List<CurvePointDTO> curvePointDTOList = curvePointService.findAll();
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("curvePoints", curvePointDTOList);
        return "curvePoint/list";
    }

    /**
     *
     * To access to the curvePoint/add page
     *
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePointDTO curvePoint, Model model) {
        model.addAttribute("curvePoint",curvePoint);
        return "curvePoint/add";
    }

    /**
     *
     * To create a new curvePoint
     *
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePointDTO curvePoint, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            curvePointService.doSave(curvePoint);
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("curvePoints", curvePointService.findAll());
            return "redirect:/curvePoint/list";
        }

        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }

    /**
     *
     * To access to the curvePoint/update page about a curvePoint
     *
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Optional<CurvePoint> curvePoint = curvePointService.findById(id);
        if(curvePoint.isEmpty()){
            User user = userService.getFullname();

            model.addAttribute("user",user.getFullname());
            model.addAttribute("errorMessage","The chosen curve point is empty");
            model.addAttribute("curvePoints", curvePointService.findAll());
            return "curvePoint/list";
        }

        model.addAttribute("curvePoint",curvePointService.getCurvePointDTO(curvePoint.get()));
        return "curvePoint/update";
    }

    /**
     *
     * To update a curvePoint
     *
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@Valid @ModelAttribute("curvePoint") CurvePointDTO updatedCurvePoint,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("curvePoint", updatedCurvePoint);
            return "curvePoint/update";
        }

        curvePointService.doSave(updatedCurvePoint);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    /**
     *
     * To delete a curvePoint
     *
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        curvePointService.doDelete(id);
        User user = userService.getFullname();

        model.addAttribute("user",user.getFullname());
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }
}
