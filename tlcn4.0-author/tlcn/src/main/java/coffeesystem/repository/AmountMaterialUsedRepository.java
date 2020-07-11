package coffeesystem.repository;

import coffeesystem.model.AmountMaterialUsed;
import coffeesystem.model.Inventory;
import coffeesystem.model.embedding.AmountMaterialUsedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AmountMaterialUsedRepository extends JpaRepository<AmountMaterialUsed, AmountMaterialUsedId> {
    Optional<AmountMaterialUsed> findByMaterialIdAndAmountMaterialUsedIdIdBranchShopAndStatus(
            Integer id,Integer idBranchShop, String status);
    List<AmountMaterialUsed> findByMaterialIdAndAmountMaterialUsedIdIdBranchShop(Integer id,Integer idBranchShop);
    @Query("select amu2 from AmountMaterialUsed amu2 where amu2.material.id=?1 " +
            "and amu2.branchShop.id=?2 and amu2.status='active' and amu2.amountMaterialUsedId.checkDate=" +
            "(select max(amu1.amountMaterialUsedId.checkDate) from AmountMaterialUsed amu1 where amu1.material.id=?1" +
            " and amu1.branchShop.id=?2 and amu1.status='active')")
    Optional<AmountMaterialUsed> findByMaxCheckDateByIdMaterialAndIdBranchShop(
            Integer idMaterial, Integer idBranchShop);
    @Query("select max(amu.amountMaterialUsedId.id) from AmountMaterialUsed amu")
    Integer findMaxId();

}
