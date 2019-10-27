package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.EmployeeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {
    List<EmployeeType> findAllByEnable(boolean enable);
    Page<EmployeeType> findAllByEnable(boolean enable, Pageable pageable);
    Optional<EmployeeType> findByNameAndEnable(String name, boolean enable);
    Optional<EmployeeType> findByIdAndEnable(Integer id, boolean enable);
}
