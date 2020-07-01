package coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.FetchProfile;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class Employee extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private boolean enable = true;
    private String idkey;
    @ManyToOne
    @JoinColumn(name = "branch_shop_id")
    private BranchShop branchShop;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Account> account;

    @ManyToOne
    @JoinColumn(name = "employeeType_id")
    private EmployeeType employeeType;
}
