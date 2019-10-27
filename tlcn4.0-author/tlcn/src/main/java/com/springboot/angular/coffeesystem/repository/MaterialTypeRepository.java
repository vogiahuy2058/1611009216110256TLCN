package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.MaterialType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface MaterialTypeRepository extends JpaRepository<MaterialType, Integer> {
    List<MaterialType> findAllByEnable(boolean enable);
    Page<MaterialType> findAllByEnable(boolean enable, Pageable pageable);
    Optional<MaterialType> findByNameAndEnable(String name, boolean enable);
    Optional<MaterialType> findByIdAndEnable(Integer id, boolean enable);
}
