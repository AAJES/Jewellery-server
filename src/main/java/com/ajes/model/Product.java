package com.ajes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Float weightage;
    private Float wastage;
    private String comment;
    private String productImage;

   private Float stoneWeight;
   private Float  stone;
   private Float labour;
   private Float processing;


    @OneToOne(cascade = {CascadeType.ALL})
    private Quantity quantity;

    @CreationTimestamp
    private Date trDate;
    long now = System.currentTimeMillis();
    private String description;

//    private Float totalRate;

    @OneToOne
    private Purity purity;

    @OneToOne
    private Category category;

    @OneToOne
    private Metal metal;


    @OneToOne
    private Booking booking;


//    @OneToOne
//    private User user;
//
//    @OneToOne
//    private Login login;

//    @OneToOne
//    private Rate rate;


    public Product() {
    }

    public Product(Long productId, String productName, Float weightage, Float wastage, String comment, String productImage, Float stoneWeight, Float stone, Float labour, Float processing, Quantity quantity, Date trDate, long now, String description, Purity purity, Category category, Metal metal, Booking booking) {
        this.productId = productId;
        this.productName = productName;
        this.weightage = weightage;
        this.wastage = wastage;
        this.comment = comment;
        this.productImage = productImage;
        this.stoneWeight = stoneWeight;
        this.stone = stone;
        this.labour = labour;
        this.processing = processing;
        this.quantity = quantity;
        this.trDate = trDate;
        this.now = now;
        this.description = description;
        this.purity = purity;
        this.category = category;
        this.metal = metal;
        this.booking = booking;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getWeightage() {
        return weightage;
    }

    public void setWeightage(Float weightage) {
        this.weightage = weightage;
    }

    public Float getWastage() {
        return wastage;
    }

    public void setWastage(Float wastage) {
        this.wastage = wastage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Purity getPurity() {
        return purity;
    }

    public void setPurity(Purity purity) {
        this.purity = purity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Metal getMetal() {
        return metal;
    }

    public void setMetal(Metal metal) {
        this.metal = metal;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Float getStoneWeight() {
        return stoneWeight;
    }

    public void setStoneWeight(Float stoneWeight) {
        this.stoneWeight = stoneWeight;
    }

    public Float getStone() {
        return stone;
    }

    public void setStone(Float stone) {
        this.stone = stone;
    }

    public Float getLabour() {
        return labour;
    }

    public void setLabour(Float labour) {
        this.labour = labour;
    }

    public Float getProcessing() {
        return processing;
    }

    public void setProcessing(Float processing) {
        this.processing = processing;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", weightage=" + weightage +
                ", wastage=" + wastage +
                ", comment='" + comment + '\'' +
                ", productImage='" + productImage + '\'' +
                ", stoneWeight=" + stoneWeight +
                ", stone=" + stone +
                ", labour=" + labour +
                ", processing=" + processing +
                ", quantity=" + quantity +
                ", trDate=" + trDate +
                ", now=" + now +
                ", description='" + description + '\'' +
                ", purity=" + purity +
                ", category=" + category +
                ", metal=" + metal +
                ", booking=" + booking +
                '}';
    }
}