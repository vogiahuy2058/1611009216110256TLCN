package coffeesystem.repository;

import coffeesystem.model.DrinkPrice;
import coffeesystem.model.embedding.DrinkPriceId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DrinkPriceRepository extends JpaRepository<DrinkPrice, DrinkPriceId> {
    Optional<DrinkPrice> findByDrinkPriceIdIdDrinkAndEnable(Integer idDrink, boolean enable);
    List<DrinkPrice> findAllByEnable(boolean enable);
    @Query("select max(dp.drinkPriceId.id) from DrinkPrice dp")
    Integer findMaxId();
    Page<DrinkPrice> findAllByEnable(boolean enable, Pageable pageable);
}
