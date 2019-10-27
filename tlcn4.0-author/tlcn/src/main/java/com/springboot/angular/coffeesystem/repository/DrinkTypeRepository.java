package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.DrinkType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface DrinkTypeRepository extends JpaRepository<DrinkType, Integer> {
    List<DrinkType> findAllByEnable(boolean enable);
    Page<DrinkType> findAllByEnable(boolean enable, Pageable pageable);
    Optional<DrinkType> findByNameAndEnable(String name, boolean enable);
    Optional<DrinkType> findByIdAndEnable(Integer id, boolean enable);
}
