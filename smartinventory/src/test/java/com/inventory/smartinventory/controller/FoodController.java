package com.inventory.smartinventory.controller;

import com.inventory.smartinventory.entity.Food;
import com.inventory.smartinventory.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FoodController {

    private final FoodService service;

    public FoodController(FoodService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("foods", service.allFoods());
        return "index";
    }

    @PostMapping("/add")
    public String add(Food food) {
        service.addFood(food);
        return "redirect:/";
    }
}
