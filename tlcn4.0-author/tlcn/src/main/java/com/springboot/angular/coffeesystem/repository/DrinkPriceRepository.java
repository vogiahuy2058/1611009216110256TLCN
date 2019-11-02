package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.DrinkPrice;
import com.springboot.angular.coffeesystem.model.embedding.DrinkPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DrinkPriceRepository extends JpaRepository<DrinkPrice, DrinkPriceId> {
    Optional<DrinkPrice> findByDrinkPriceIdIdDrinkAndEnable(Integer idDrink, boolean enable);
    List<DrinkPrice> findAllByEnable(boolean enable);
    @Query("select max(dp.drinkPriceId.id) from DrinkPrice dp")
    Integer findMaxId();
}
