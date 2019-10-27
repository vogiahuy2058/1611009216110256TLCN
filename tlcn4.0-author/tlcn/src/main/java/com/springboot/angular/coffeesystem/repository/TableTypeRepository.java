package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.CoffeeTable;
import com.springboot.angular.coffeesystem.model.TableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface TableTypeRepository extends JpaRepository<TableType, Integer> {
    List<TableType> findAllByEnable(boolean enable);
    Page<TableType> findAllByEnable(boolean enable, Pageable pageable);
    Optional<TableType> findByNameAndEnable(String name, boolean enable);
    Optional<TableType> findByIdAndEnable(Integer id, boolean enable);

}
