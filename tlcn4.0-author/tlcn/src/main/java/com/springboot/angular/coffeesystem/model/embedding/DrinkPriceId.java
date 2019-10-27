package com.springboot.angular.coffeesystem.model.embedding;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class DrinkPriceId implements Serializable {
    @Column(name = "id_drink")
    private Integer id;
    @Column(name = "date_drink")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkPriceId drinkPriceId = (DrinkPriceId) o;
        return id.equals(drinkPriceId.id) &&
                date.equals(drinkPriceId.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }
}
