package com.springboot.angular.coffeesystem.model;

import com.springboot.angular.coffeesystem.model.embedding.DrinkPriceId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drink_price")
public class DrinkPrice {

    @EmbeddedId
    private DrinkPriceId drinkPriceId;
    private float price;
    private float initialPrice;
    @ManyToOne
    @JoinColumn(name = "drink_drink_id")
    private Drink drink;
}
