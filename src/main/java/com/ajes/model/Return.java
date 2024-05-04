package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "return_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnId;
    private Float  wastage;
    private Float grossWeight;
    private Float netWeight;

    @CreationTimestamp
    private Date trdate;
//    private Time trtime;
    private String description;
    private Float returnAmount;

    @OneToOne
    private Category category;

    @OneToOne
    private Metal metal;

    @OneToOne
    private Purity purity;

    @OneToOne
    private User user;

    @OneToOne
    private Login login;

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Float getWastage() {
        return wastage;
    }

    public void setWastage(Float wastage) {
        this.wastage = wastage;
    }

    public Float getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Float grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Float getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Float netWeight) {
        this.netWeight = netWeight;
    }

    public Date getTrdate() {
        return trdate;
    }

    public void setTrdate(Date trdate) {
        this.trdate = trdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Float returnAmount) {
        this.returnAmount = returnAmount;
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

    public Purity getPurity() {
        return purity;
    }

    public void setPurity(Purity purity) {
        this.purity = purity;
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
}
