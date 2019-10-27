package com.springboot.angular.coffeesystem.repository;


import com.springboot.angular.coffeesystem.model.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer> {
    Optional<CustomerType> findByNameAndEnable(String name, boolean enable);
    Optional<CustomerType> findByIdAndEnable(Integer id, boolean enable);
    Page<CustomerType> findAllByEnable(boolean enable, Pageable pageable);
    List<CustomerType> findAllByEnable(boolean enable);
}
