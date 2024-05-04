package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private Float totalCost;
    private Integer quantity;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date trDate = new Date();



    @OneToMany(cascade = {CascadeType.ALL})
    private List<Product> products;

    @OneToOne
    private User user;

    @OneToOne
    private Login login;


    public Cart() {
        super();
    }


    public Cart(Integer cartId, Float totalCost, Integer quantity, Date trDate,  List<Product> products, User user, Login login) {
        this.cartId = cartId;
        this.totalCost = totalCost;
        this.quantity = quantity;
        this.trDate = trDate;

        this.products = products;
        this.user = user;
        this.login = login;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }



    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", totalCost=" + totalCost +
                ", quantity=" + quantity +
                ", trDate=" + trDate +

                ", products=" + products +
                ", user=" + user +
                ", login=" + login +
                '}';
    }
}
