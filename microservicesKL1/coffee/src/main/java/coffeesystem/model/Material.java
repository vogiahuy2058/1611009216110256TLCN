package coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "material")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Material extends Auditable<String>{

//    @EmbeddedId
//    private MaterialPriceId materialId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NaturalId
    private String name;
    private float inventory;
    private boolean enable = true;

    private float minInventory;
    private float maxInventory;

    @ManyToOne
    @JoinColumn(name = "material_type_id")
    private MaterialType materialType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade = CascadeType.ALL)
    private Set<SupplyContractDetail> supplyContractDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade = CascadeType.ALL)
    private Set<MaterialPrice> materialPrices;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
