package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;
//    private Integer productId;
//    private Integer paymentId;


    private String billNumber;
    private Float amountPaid;
    private Float discountAmount;
    private Float actualAmount;
    private Float gst;
//    private Integer customerId;

    @CreationTimestamp
    private Date trdate;


    @OneToMany
    @Column(unique = false, nullable = true)
    private List<Product> products;

    @OneToOne
    private User user;

    @OneToOne
    private Payment payment;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Login login;

    public Bill() {
    }

    public Bill(Long billId, String billNumber, Float amountPaid, Float discountAmount, Float actualAmount, Float gst, Date trdate, List<Product> products, User user, Payment payment, Customer customer, Login login) {
        this.billId = billId;
        this.billNumber = billNumber;
        this.amountPaid = amountPaid;
        this.discountAmount = discountAmount;
        this.actualAmount = actualAmount;
        this.gst = gst;
        this.trdate = trdate;
        this.products = products;
        this.user = user;
        this.payment = payment;
        this.customer = customer;
        this.login = login;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Float getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Float actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Float getGst() {
        return gst;
    }

    public void setGst(Float gst) {
        this.gst = gst;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", billNumber='" + billNumber + '\'' +
                ", amountPaid=" + amountPaid +
                ", discountAmount=" + discountAmount +
                ", actualAmount=" + actualAmount +
                ", gst=" + gst +
                ", trdate=" + trdate +
                ", products=" + products +
                ", user=" + user +
                ", payment=" + payment +
                ", customer=" + customer +
                ", login=" + login +
                '}';
    }
}

