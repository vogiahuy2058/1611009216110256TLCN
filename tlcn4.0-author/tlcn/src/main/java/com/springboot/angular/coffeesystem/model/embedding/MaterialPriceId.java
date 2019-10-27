package com.springboot.angular.coffeesystem.model.embedding;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class MaterialPriceId implements Serializable {
    @Column(name = "id_material")
    private Integer id;
    @Column(name = "date_material")
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
        MaterialPriceId that = (MaterialPriceId) o;
        return id.equals(that.id) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }
}
