package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Float amountPaid;
    private Float totalWeight;
    private Float discountAmount;
    private Float totalAmount;
    private String paymentReference;
    private Boolean paymentStatus;
//    private  Long userId;
//    private Integer loginId;

    @CreationTimestamp
    private Date trdate;


    @OneToMany
    @Column(unique = false,nullable = true)
    private List<Product> products;

    @OneToOne
    private Customer customer;

//    @OneToOne
//    private Sales sales;

//    @OneToOne
//    private PaymentType paymentType;

    @OneToOne(cascade = {CascadeType.ALL})
    private ModeOfPayment modeOfPayment;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    @OneToOne
    private Login login;

    @OneToOne
    private  BankDetails bankDetails;


    public Payment() {
    }

    public Payment(Long paymentId, Float amountPaid, Float totalWeight, Float discountAmount, Float totalAmount,
                   String paymentReference, Boolean paymentStatus, Date trdate, List<Product> products, Customer customer,
                   ModeOfPayment modeOfPayment, User user, Login login, BankDetails bankDetails) {
        this.paymentId = paymentId;
        this.amountPaid = amountPaid;
        this.totalWeight = totalWeight;
        this.discountAmount = discountAmount;
        this.totalAmount = totalAmount;
        this.paymentReference = paymentReference;
        this.paymentStatus = paymentStatus;
        this.trdate = trdate;
        this.products = products;
        this.customer = customer;
        this.modeOfPayment = modeOfPayment;
        this.user = user;
        this.login = login;
        this.bankDetails = bankDetails;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getTrdate() {
        return trdate;
    }

    public void setTrdate(Date trdate) {
        this.trdate = trdate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
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

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amountPaid=" + amountPaid +
                ", totalWeight=" + totalWeight +
                ", discountAmount=" + discountAmount +
                ", totalAmount=" + totalAmount +
                ", paymentReference='" + paymentReference + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", trdate=" + trdate +
                ", products=" + products +
                ", customer=" + customer +
                ", modeOfPayment=" + modeOfPayment +
                ", user=" + user +
                ", login=" + login +
                ", bankDetails=" + bankDetails +
                '}';
    }
}
