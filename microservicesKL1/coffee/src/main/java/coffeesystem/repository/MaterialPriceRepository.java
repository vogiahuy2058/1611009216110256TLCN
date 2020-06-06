package coffeesystem.repository;


import coffeesystem.model.MaterialPrice;
import coffeesystem.model.embedding.MaterialPriceId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MaterialPriceRepository extends JpaRepository<MaterialPrice, MaterialPriceId> {
//    @Query("select mp from MaterialPrice mp where mp.materialPriceId.idMaterial=?1 and mp.enable=?2")
    Optional<MaterialPrice> findByMaterialPriceIdIdMaterialAndEnable(Integer idMaterial, boolean enable);
    @Query("select max(mp.materialPriceId.id) from MaterialPrice mp")
    Integer findMaxId();
    List<MaterialPrice> findAllByEnable(boolean enable);
    Page<MaterialPrice> findAllByEnable(boolean enable, Pageable pageable);
}
