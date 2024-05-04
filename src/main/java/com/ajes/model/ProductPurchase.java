package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class ProductPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productPurchaseId;

    @CreationTimestamp
    private Date trDate;
    private Float totalWeight;
    private Float amountPaid;
    private String status;

    private Integer quantity;

    @OneToOne
    private Product product;

//    @OneToOne
//    private Stock stock;

    @OneToOne
    private User user;

    @OneToOne
    private Employee employee;

    @OneToOne
    private Login login;

    public ProductPurchase() {
    }

    public ProductPurchase(Long productPurchaseId, Date trDate, Float totalWeight, Float amountPaid, String status,
                           Integer quantity, Product product, User user, Employee employee, Login login) {
        this.productPurchaseId = productPurchaseId;
        this.trDate = trDate;
        this.totalWeight = totalWeight;
        this.amountPaid = amountPaid;
        this.status = status;
        this.quantity = quantity;
        this.product = product;
        this.user = user;
        this.employee = employee;
        this.login = login;
    }

    public Long getProductPurchaseId() {
        return productPurchaseId;
    }

    public void setProductPurchaseId(Long productPurchaseId) {
        this.productPurchaseId = productPurchaseId;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public Float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status
        ;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "ProductPurchase{" +
                "productPurchaseId=" + productPurchaseId +
                ", trDate=" + trDate +
                ", totalWeight=" + totalWeight +
                ", amountPaid=" + amountPaid +
                ", status=" + status +
                ", quantity=" + quantity +
                ", product=" + product +
                ", user=" + user +
                ", employee=" + employee +
                ", login=" + login +
                '}';
    }
}
