package coffeesystem.repository;

import coffeesystem.model.EmployeeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {
    List<EmployeeType> findAllByEnable(boolean enable);
    Page<EmployeeType> findAllByEnable(boolean enable, Pageable pageable);
    Optional<EmployeeType> findByNameAndEnable(String name, boolean enable);
    Optional<EmployeeType> findByIdAndEnable(Integer id, boolean enable);
    @Query("select max(e.id) from EmployeeType e")
    Integer findMaxId();
}
