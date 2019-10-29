package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.DrinkPrice;
import com.springboot.angular.coffeesystem.model.embedding.DrinkPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DrinkPriceRepository extends JpaRepository<DrinkPrice, DrinkPriceId> {
    Optional<DrinkPrice> findByDrinkPriceIdIdAndEnable(Integer id, boolean enable);

}
