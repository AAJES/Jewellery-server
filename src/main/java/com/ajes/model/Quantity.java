package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor

public class Quantity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false)
    private Integer quantityId;
    private Integer quantity;

    public Quantity() {
    }

    public Quantity(Integer quantityId, Integer quantity) {
        this.quantityId = quantityId;
        this.quantity = quantity;
    }

    public Integer getQuantityId() {
        return quantityId;
    }

    public void setQuantityId(Integer quantityId) {
        this.quantityId = quantityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "quantityId=" + quantityId +
                ", quantity=" + quantity +
                '}';
    }
}
