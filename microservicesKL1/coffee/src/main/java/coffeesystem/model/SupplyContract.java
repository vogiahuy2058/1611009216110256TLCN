package coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "supply_contract")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplyContract extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateCreate;
    private float totalPrice;
    private boolean enable = true;

    //status=0: chua xu ly
    //status=1: khong duyet yeu cau
    //status=2: duyet yeu cau
    //status=3: da in phieu nhap
    //status=4: da nhan hang
    //status=5: da thanh toan
    //status=6: hoan thanh
    private Integer status;
    private LocalDate deliveryTime;
    private LocalDate paymentTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplyContract", cascade = CascadeType.ALL)
    private Set<SupplyContractDetail> supplyContractDetails;

    @ManyToOne
    @JoinColumn(name = "branch_shop")
    private BranchShop branchShop;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
