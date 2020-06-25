package coffeesystem.repository;

import coffeesystem.model.Inventory;
import coffeesystem.model.MinMaxInventory;
import coffeesystem.model.embedding.InventoryId;
import coffeesystem.model.embedding.MinMaxInventoryId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    Optional<Inventory> findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndEnable(
            Integer idMaterial, Integer idBranchShop, LocalDate firstDate, boolean enable);
    Optional<Inventory> findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndStatusAndEnable(
            Integer idMaterial, Integer idBranchShop, LocalDate firstDate, String status, boolean enable);
    List<Inventory> findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(
            Integer idMaterial, Integer idBranchShop, boolean enable);
    @Query("select iii from Inventory iii where iii.inventoryId.idMaterial=?1 " +
            "and iii.inventoryId.idBranchShop=?2 and iii.enable=?3 and iii.inventoryId.firstDate=" +
            "(select max(ii.inventoryId.firstDate) from Inventory ii where ii.inventoryId.idMaterial=?1" +
            " and ii.inventoryId.idBranchShop=?2 and ii.enable=?3)")
    Optional<Inventory> findByMaxFirstDateByIdMaterialAndIdBranchShopAndEnable(
            Integer idMaterial, Integer idBranchShop, boolean enable);
//    @Query("select max(mmi.minMaxInventoryId.id) from MinMaxInventory mmi")
//    Integer findMaxId();
    List<Inventory> findAllByEnable(boolean enable);
    Page<Inventory> findAllByEnable(boolean enable, Pageable pageable);
    List<Inventory> findByInventoryIdIdMaterial(Integer idMaterial);
    List<Inventory> findByInventoryIdIdBranchShop(Integer idBranchShop);
    Page<Inventory> findByInventoryIdIdBranchShopAndEnable(
            Integer idBranchShop, boolean enable, Pageable pageable);
}
