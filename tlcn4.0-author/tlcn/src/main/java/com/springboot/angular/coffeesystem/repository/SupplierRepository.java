package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findAllByEnable(boolean enable);
    Page<Supplier> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Supplier> findAllByNameAndEnable(String name, boolean enable);
    Optional<Supplier> findByIdAndEnable(Integer id, boolean enable);
}
