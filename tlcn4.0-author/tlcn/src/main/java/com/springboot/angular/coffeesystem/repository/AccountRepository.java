package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(rollbackFor = Exception.class)
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a join fetch a.role where a.username = ?1")
    Account findRoleUsername(String username);
    @Query("select case when count(a) > 0 then true else false end from Account a where a.username = ?1 or a.email = ?1")
    Boolean isExists(String value);
    Optional<Account> findByUsernameAndEnable(String username, boolean enable);
    Optional<Account> findByEmployeeId(Integer id);
    Page<Account> findAllByEnable(boolean enable, Pageable pageable);
    List<Account> findAllByEnable(boolean enable);

}
