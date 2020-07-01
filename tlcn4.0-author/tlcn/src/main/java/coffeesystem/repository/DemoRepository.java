package coffeesystem.repository;

import coffeesystem.model.Customer;
import coffeesystem.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoRepository extends JpaRepository<Demo, Integer> {
    List<Demo> findByLabelAndStatus(String label, String status);
}
