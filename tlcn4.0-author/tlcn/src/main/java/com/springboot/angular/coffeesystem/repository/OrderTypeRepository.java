package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.OrderType;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {
   Optional<OrderType> findByNameAndEnable(String name, boolean enable);
   Optional<OrderType> findByIdAndEnable(Integer id, boolean enable);
   Page<OrderType> findAllByEnable(boolean enable, Pageable pageable);
   List<OrderType> findAllByEnable(boolean enable);
}
