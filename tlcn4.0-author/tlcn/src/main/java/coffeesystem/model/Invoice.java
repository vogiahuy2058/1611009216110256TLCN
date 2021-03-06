package coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private float vat;
    private float totalPrice;
    private float realPay;
    private float totalDiscount;
//    private LocalDateTime date;
    private ZonedDateTime date;
    private boolean enable = true;
    //status=0: chua thanh toan
    //status=1: da thanh toan
    //status=2: da in phieu dan tren ly thuc uong
    private Integer status;
    private Integer numberPosition;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "branch_shop_id")
    private BranchShop branchShop;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.ALL)
    private Set<InvoiceDetail> invoiceDetails;
//
//    @ManyToOne
//    @JoinColumn(name = "table_id", nullable = true)
//    private CoffeeTable coffeeTable;


    @ManyToOne
    @JoinColumn(name = "order_type_id")
    private OrderType orderType;
}
