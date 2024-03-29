package coffeesystem.repository;


import coffeesystem.model.BranchShop;
import coffeesystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface BranchShopRepository extends JpaRepository<BranchShop, Integer> {
    List<BranchShop> findAllByEnable(boolean enable);
    Page<BranchShop> findAllByEnable(boolean enable, Pageable pageable);
    Optional<BranchShop> findByNameAndEnable(String name, boolean enable);
    Optional<BranchShop> findByIdAndEnable(Integer id, boolean enable);
    Optional<BranchShop> findByEmployees(Employee employee);

}
