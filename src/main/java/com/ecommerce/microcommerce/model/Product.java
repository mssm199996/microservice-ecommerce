package com.ecommerce.microcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @Length(min = 3, max = 20, message = "Name's too long or too short")
    private String name;

    @Min(value = 1)
    private int price;
    private int buyingPrice;

    @Transient
    public Integer getMarge() {
        return this.price - this.buyingPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + name + '\'' +
                ", prix=" + price +
                '}';
    }
}
