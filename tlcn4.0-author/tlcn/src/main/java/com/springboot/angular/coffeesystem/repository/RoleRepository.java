package com.springboot.angular.coffeesystem.repository;


import com.springboot.angular.coffeesystem.model.Role;
import com.springboot.angular.coffeesystem.model.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional(rollbackFor = Exception.class)
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNameAndEnable(RoleName roleName, boolean enable);
    List<Role> findAllByEnable(boolean enable);
    Page<Role> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Role> findByIdAndEnable(Integer id, boolean enable);
}
