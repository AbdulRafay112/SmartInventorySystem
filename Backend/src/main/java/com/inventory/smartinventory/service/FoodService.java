package com.inventory.smartinventory.service;

import com.inventory.smartinventory.entity.Food;
import com.inventory.smartinventory.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository repo;

    public FoodService(FoodRepository repo) {
        this.repo = repo;
    }

    public Food addFood(Food food) {
        final Food[] savedFood = { null };

        repo.findByItemNameAndPrice(food.getItemName(), food.getPrice())
                .ifPresentOrElse(existing -> {
                    existing.setQuantity(existing.getQuantity() + food.getQuantity());
                    savedFood[0] = repo.save(existing); 
                }, () -> {
                    food.setAddedDate(LocalDate.now());
                    savedFood[0] = repo.save(food); 
                });

        return savedFood[0]; 
    }

    public void removeFood(Long id, int qty) {
        Food food = repo.findById(id).orElseThrow();
        int newQty = food.getQuantity() - qty;

        if (newQty > 0) {
            food.setQuantity(newQty);
            repo.save(food);
        } else {
            repo.delete(food);
        }
    }

    public List<Food> expiredItems() {
        return repo.findByExpiryDateLessThanEqual(LocalDate.now());
    }

    public List<Food> allFoods() {
        return repo.findAll();
    }
}
