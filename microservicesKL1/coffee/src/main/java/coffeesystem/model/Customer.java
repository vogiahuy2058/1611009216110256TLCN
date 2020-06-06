package coffeesystem.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phone;
    private boolean enable = true;
    private String address;
    private LocalDate birthDay;
    private boolean sex;

    private String email;
    private float totalPurchase;
    private String note;
    @ManyToOne
    @JoinColumn(name = "CustomerType_id")
    private CustomerType customerType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

}
