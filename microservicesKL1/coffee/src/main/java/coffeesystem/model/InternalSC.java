package coffeesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "internal_supply_contract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternalSC extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateCreate;
    private boolean enable = true;

    //status=0: chua xu ly
    //status=1: khong duyet yeu cau
    //status=2: duyet yeu cau
    //status=3: da in phieu xuat
    //status=4: da xuat kho tong
    //status=5: da nhan hang
    //status=6: hoan thanh
    private Integer status;
    private LocalDate deliveryTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "internalSC", cascade = CascadeType.ALL)
    private Set<InternalSCDetail> internalSCDetails;

    @ManyToOne
    @JoinColumn(name = "branch_shop")
    private BranchShop branchShop;
}
