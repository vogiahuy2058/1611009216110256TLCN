package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findAllByEnable(boolean enable);
    Page<Material> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Material> findByNameAndEnable(String name, boolean enable);
    Optional<Material> findByIdAndEnable(Integer id, boolean enable);
    List<Material> findByMaterialTypeId(Integer id);
}
