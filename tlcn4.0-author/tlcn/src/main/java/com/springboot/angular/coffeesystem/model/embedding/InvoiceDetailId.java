package com.springboot.angular.coffeesystem.model.embedding;

import com.springboot.angular.coffeesystem.model.Invoice;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class InvoiceDetailId implements Serializable {
    @Column(name = "invoice_id")
    private Integer invoiceId;
    @Column(name = "drink_id")
    private Integer drinkId;

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, drinkId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;
        InvoiceDetailId that = (InvoiceDetailId) obj;
        return Objects.equals(invoiceId, that.invoiceId) && Objects.equals(drinkId, that.drinkId);
    }

}
