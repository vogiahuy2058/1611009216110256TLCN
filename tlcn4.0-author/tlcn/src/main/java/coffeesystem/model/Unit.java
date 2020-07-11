package coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "unit")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Unit extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NaturalId
    private String name;
    private boolean enable = true;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit", cascade = CascadeType.ALL)
    private Set<Material> materials;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit", cascade = CascadeType.ALL)
    private Set<SupplyContractDetail> supplyContractDetails;

}
