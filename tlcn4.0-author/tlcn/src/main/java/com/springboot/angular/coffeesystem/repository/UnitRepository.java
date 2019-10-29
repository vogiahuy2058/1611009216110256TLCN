package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    Optional<Unit> findByNameAndEnable(String name, boolean enable);
}
