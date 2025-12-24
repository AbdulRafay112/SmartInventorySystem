package com.inventory.smartinventory.repository;

import com.inventory.smartinventory.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByItemNameAndPrice(String itemName, int price);

    List<Food> findByExpiryDateLessThanEqual(LocalDate date);
}
