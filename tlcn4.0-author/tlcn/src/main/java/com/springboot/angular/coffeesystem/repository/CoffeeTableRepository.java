package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.CoffeeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface CoffeeTableRepository extends JpaRepository<CoffeeTable, Integer> {
    Optional<CoffeeTable>  findByName(String name);
    List<CoffeeTable> findAllByEnable(boolean enable);
    Page<CoffeeTable> findAllByEnable(boolean enable, Pageable pageable);
    Optional<CoffeeTable> findByIdAndEnable(Integer id, boolean enable);
    List<CoffeeTable> findByTableTypeId(Integer id);
}
