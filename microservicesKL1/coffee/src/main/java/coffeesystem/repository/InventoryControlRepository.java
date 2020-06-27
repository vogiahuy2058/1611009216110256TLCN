package coffeesystem.repository;

import coffeesystem.model.CustomerType;
import coffeesystem.model.Inventory;
import coffeesystem.model.InventoryControl;
import coffeesystem.model.embedding.InventoryId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InventoryControlRepository extends JpaRepository<InventoryControl, InventoryId> {
    Optional<InventoryControl> findByCheckDateAndStatus(LocalDate checkDate, String status);
    Page<InventoryControl> findAllByStatus(String status, Pageable pageable);
    List<InventoryControl> findAllByStatus(String status);
    Optional<InventoryControl> findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndEnable(
            Integer idMaterial, Integer idBranchShop, LocalDate firstDate, boolean enable);
    Optional<InventoryControl> findByInventoryIdIdAndEnable(Integer id, boolean enable);
    List<InventoryControl> findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndStatusAndEnable(
            Integer idMaterial, Integer idBranchShop, LocalDate firstDate, String status, boolean enable);
    List<InventoryControl> findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(
            Integer idMaterial, Integer idBranchShop, boolean enable);
    @Query("select iii from InventoryControl iii where iii.inventoryId.idMaterial=?1 " +
            "and iii.inventoryId.idBranchShop=?2 and iii.enable=?3 and iii.inventoryId.firstDate=" +
            "(select max(ii.inventoryId.firstDate) from InventoryControl ii where ii.inventoryId.idMaterial=?1" +
            " and ii.inventoryId.idBranchShop=?2 and ii.enable=?3)")
    Optional<InventoryControl> findByMaxFirstDateByIdMaterialAndIdBranchShopAndEnable(
            Integer idMaterial, Integer idBranchShop, boolean enable);
    @Query("select max(ii.inventoryId.id) from InventoryControl ii")
    Integer findMaxId();
    List<InventoryControl> findAllByEnable(boolean enable);
    Page<InventoryControl> findAllByEnable(boolean enable, Pageable pageable);
    //    List<Inventory> findByInventoryIdIdMaterial(Integer idMaterial);
//    List<Inventory> findByInventoryIdIdBranchShop(Integer idBranchShop);
    Page<InventoryControl> findByInventoryIdIdBranchShopAndEnable(
            Integer idBranchShop, boolean enable, Pageable pageable);
    List<InventoryControl> findAllByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndStatusAndEnable(
            Integer idMaterial, Integer idBranchShop,String status, boolean enable);
    List<InventoryControl> findAllByStatusAndEnable(String status, boolean enable);
    Page<InventoryControl> findAllByStatusAndEnable(String status, boolean enable, Pageable pageable);
}
