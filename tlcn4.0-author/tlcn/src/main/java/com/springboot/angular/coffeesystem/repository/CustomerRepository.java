package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByEnable(boolean enable);
    Page<Customer> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Customer> findByPhone(String phone);
    Optional<Customer> findByIdAndEnable(Integer id, boolean enable);
    List<Customer> findByCustomerTypeId(Integer id);

}
