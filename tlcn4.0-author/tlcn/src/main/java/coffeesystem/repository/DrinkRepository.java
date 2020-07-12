package coffeesystem.repository;


import coffeesystem.model.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    List<Drink> findAllByEnable(boolean enable);
    Page<Drink> findAllByEnable(boolean enable,Pageable pageable);
    Optional<Drink> findByNameAndEnable(String name, boolean enable);
    Optional<Drink> findByIdAndEnable(Integer id, boolean enable);
    List<Drink> findByDrinkTypeIdAndEnable(Integer id, boolean enable);
    List<Drink> findByDrinkTypeNameAndEnable(String name, boolean enable);
    @Query("select d from Drink d where d.id  in " +
            "(SELECT dp.drinkPriceId.idDrink FROM DrinkPrice dp) " +
            "and d.drinkType.name=?1 and d.enable=true")
    List<Drink> findDrinkHavePriceByDrinkType(String name);
    @Query("select d from Drink d where d.id  in " +
            "(SELECT dp.drinkPriceId.idDrink FROM DrinkPrice dp) " +
            "and d.drinkType.name=?1 and d.enable=true")
    List<Drink> findDrinkHavePriceAndHaveRecipeByDrinkType(String name);
    @Query("select d from Drink d where d.id  in " +
            "(SELECT dp.drinkPriceId.idDrink FROM DrinkPrice dp) " +
            "and d.enable=true")
    List<Drink> findAllDrinkHavePrice();
}
