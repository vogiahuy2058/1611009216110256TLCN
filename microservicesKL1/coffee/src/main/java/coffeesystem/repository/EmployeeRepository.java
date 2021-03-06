package coffeesystem.repository;


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
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByEnable(boolean enable);
    Page<Employee> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Employee> findByIdAndEnable(Integer id, boolean enable);
    List<Employee> findByBranchShopIdAndEnable(Integer id, boolean enable);
    Page<Employee> findByBranchShopIdAndEnable(Integer id, boolean enable, Pageable pageable);
    List<Employee> findByEmployeeTypeId(Integer id);
    Optional<Employee> findByAccountId(Integer id);
    Optional<Employee> findByAccountUsername(String username);
    @Query("select e from Employee e where e not in " +
            "(SELECT a.employee FROM Account a) " +
            "and e.employeeType.name=?1 and e.enable=true")
    List<Employee> findEmployeeNotHaveAccountByEmployeeType(String name);
    @Query("select max(e.id) from Employee e")
    Integer findMaxId();

}
