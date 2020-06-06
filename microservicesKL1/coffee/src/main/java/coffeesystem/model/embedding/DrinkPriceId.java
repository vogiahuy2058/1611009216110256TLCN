package coffeesystem.model.embedding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class DrinkPriceId implements Serializable {
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_drink")
    private Integer idDrink;
    @Column(name = "date_drink")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkPriceId that = (DrinkPriceId) o;
        return id.equals(that.id) &&
                idDrink.equals(that.idDrink) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDrink, date);
    }
}
