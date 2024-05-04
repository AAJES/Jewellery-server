package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;
    private Integer minQty;
    private Integer stockQtyInHand;

    private Integer totalStock;
    private Integer count;
    private Float amountPaid;

    @CreationTimestamp
    private Date trDate;

    @OneToOne
    private ProductPurchase productPurchase;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

//    @OneToOne
//    private Quantity quantity;
//
//    @OneToOne
//    private Category category;
//
//    @OneToOne
//    private Purity purity;
//
//    @OneToOne
//    private Metal metal;

    @OneToOne
    private Login login;

    public Stock() {
    }

    public Stock(Long stockId, Integer minQty, Integer stockQtyInHand, Integer totalStock, Integer count,
                 Float amountPaid,
                 Date trDate, ProductPurchase productPurchase, Product product, Login login) {
        this.stockId = stockId;
        this.minQty = minQty;
        this.stockQtyInHand = stockQtyInHand;
        this.totalStock = totalStock;
        this.count = count;
        this.amountPaid = amountPaid;
        this.trDate = trDate;
        this.productPurchase = productPurchase;
        this.product = product;
        this.login = login;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getMinQty() {
        return minQty;
    }

    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }

    public Integer getStockQtyInHand() {
        return stockQtyInHand;
    }

    public void setStockQtyInHand(Integer stockQtyInHand) {
        this.stockQtyInHand = stockQtyInHand;
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public ProductPurchase getProductPurchase() {
        return productPurchase;
    }

    public void setProductPurchase(ProductPurchase productPurchase) {
        this.productPurchase = productPurchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", minQty=" + minQty +
                ", stockQtyInHand=" + stockQtyInHand +
                ", totalStock=" + totalStock +
                ", count=" + count +
                ", amountPaid=" + amountPaid +
                ", trDate=" + trDate +
                ", productPurchase=" + productPurchase +
                ", product=" + product +
                ", login=" + login +
                '}';
    }
}
