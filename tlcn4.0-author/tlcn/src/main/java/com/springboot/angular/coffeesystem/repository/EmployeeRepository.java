package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByEnable(boolean enable);
    Page<Employee> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Employee> findByIdAndEnable(Integer id, boolean enable);
    List<Employee> findByBranchShopId(Integer id);
    List<Employee> findByEmployeeTypeId(Integer id);
    Optional<Employee> findByAccountId(Integer id);
}
