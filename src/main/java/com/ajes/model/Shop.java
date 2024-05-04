package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer shopId;
    private String shopName;
    private String logo;
    private String address;
    private String email;
    private String state;
    //private String phone;


    private String gstNo;

    private String panNo;
    @OneToOne
//    @Column(nullable = false)
    private Owner owner;

    @OneToOne
    private BankDetails bankDetails;

    public Shop() {
    }

    public Shop(Integer shopId, String shopName, String logo, String address, String email, String state, String gstNo, String panNo, Owner owner, BankDetails bankDetails) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.logo = logo;
        this.address = address;
        this.email = email;
        this.state = state;
        this.gstNo = gstNo;
        this.panNo = panNo;
        this.owner = owner;
        this.bankDetails = bankDetails;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", logo='" + logo + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", gstNo='" + gstNo + '\'' +
                ", panNo='" + panNo + '\'' +
                ", owner=" + owner +
                ", bankDetails=" + bankDetails +
                '}';
    }
}
