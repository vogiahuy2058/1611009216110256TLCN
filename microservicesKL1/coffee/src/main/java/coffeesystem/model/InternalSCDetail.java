package coffeesystem.model;

import coffeesystem.model.embedding.InternalSCDetailId;
import coffeesystem.model.embedding.SupplyContractDetailId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "internal_supply_contract_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InternalSCDetail extends Auditable<String>{
    @EmbeddedId
    private InternalSCDetailId internalSCDetailId;

    @ManyToOne
    @MapsId("materialId")
    private Material material;

    @ManyToOne
    @MapsId("internalSCId")
    private InternalSC internalSC;
//
//    private float unitPrice;
    private float numberOfRequest;
    private float quantityAllowed;
    private float quantityReceived;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
