package com.inventory.smartinventory.controller;

import com.inventory.smartinventory.entity.Food;
import com.inventory.smartinventory.service.FoodService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.inventory.smartinventory.pdf.PdfService;
import jakarta.servlet.http.HttpServletResponse;

@RestController 
@RequestMapping("/api/food") 
@CrossOrigin("*") 
public class FoodController {
    private final PdfService pdfService;
    private final FoodService service;

    public FoodController(FoodService service, PdfService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @GetMapping("/all")
    public List<Food> getAllFoods() {
        return service.allFoods();
    }

    @GetMapping("/pdf")
    public void downloadPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=inventory.pdf");

            pdfService.generate(service.allFoods(), response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add")
    public Food addFood(@RequestBody Food food) {
        return service.addFood(food);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        
        
        service.removeFood(id, 1); 
        return "Item removed/quantity updated!";
    }
}