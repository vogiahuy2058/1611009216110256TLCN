package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    List<Drink> findAllByEnable(boolean enable);
    Page<Drink> findAllByEnable(boolean enable,Pageable pageable);
    Optional<Drink> findByNameAndEnable(String name, boolean enable);
    Optional<Drink> findByIdAndEnable(Integer id, boolean enable);
    List<Drink> findByDrinkTypeId(Integer id);
}
