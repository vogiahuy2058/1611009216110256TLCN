package coffeesystem.repository;

import coffeesystem.model.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    Optional<Unit> findByNameAndEnable(String name, boolean enable);
    Optional<Unit> findByIdAndEnable(Integer id, boolean enable);
    List<Unit> findAllByEnable(boolean enable);
    Page<Unit> findAllByEnable(boolean enable, Pageable pageable);
}
