package coffeesystem.model;


import coffeesystem.model.embedding.SupplyContractDetailId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "supply_contract_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplyContractDetail extends Auditable<String> {
    @EmbeddedId
    private SupplyContractDetailId supplyContractDetailId;

    @ManyToOne
    @MapsId("materialId")
    private Material material;

    @ManyToOne
    @MapsId("supplyContractId")
    private SupplyContract supplyContract;

    private float unitPrice;
    private float amount;
    private LocalDate deliveryTime;
    private LocalDate paymentTime;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

}
