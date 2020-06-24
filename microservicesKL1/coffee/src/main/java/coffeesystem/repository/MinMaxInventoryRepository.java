package coffeesystem.repository;

import coffeesystem.model.Employee;
import coffeesystem.model.MaterialPrice;
import coffeesystem.model.MinMaxInventory;
import coffeesystem.model.embedding.MinMaxInventoryId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MinMaxInventoryRepository extends JpaRepository<MinMaxInventory, MinMaxInventoryId> {
    Optional<MinMaxInventory> findByMinMaxInventoryIdIdMaterialAndMinMaxInventoryIdIdBranchShopAndEnable(
            Integer idMaterial, Integer idBranchShop, boolean enable);
    @Query("select max(mmi.minMaxInventoryId.id) from MinMaxInventory mmi")
    Integer findMaxId();
    List<MinMaxInventory> findAllByEnable(boolean enable);
    Page<MinMaxInventory> findAllByEnable(boolean enable, Pageable pageable);
    List<MinMaxInventory> findByMinMaxInventoryIdIdMaterial(Integer idMaterial);
    List<MinMaxInventory> findByMinMaxInventoryIdIdBranchShop(Integer idBranchShop);
    Page<MinMaxInventory> findByMinMaxInventoryIdIdBranchShopAndEnable(
            Integer idBranchShop, boolean enable, Pageable pageable);
    @Query("select m from Material m where m not in " +
            "(SELECT mmi.material FROM MinMaxInventory mmi) " +
            "and m.enable=true")
    List<MinMaxInventory> findMinMaxInventoryNotCreateYet();
}
