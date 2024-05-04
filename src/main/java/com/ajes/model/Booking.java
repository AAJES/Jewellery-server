package com.ajes.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private String  modeOfAdvance;
    private Date deliveryDate;
    private Float advanceAmount;

    private Float oldMetalWeight;



    @OneToOne
    private Customer customer;

    @OneToOne
    private Metal metal;

    @OneToOne
    private Purity purity;

    private Float weight;

    private String image;

    private String catalog;

    @OneToOne
    private Employee employee;

    @CreationTimestamp
    private Date bookingDate;

    private String status;

    private Date cancelDate;

    private Float deductAmount;

    private Float refundAmount;

    private Integer quantity;

    private String bookingType;


    @OneToOne
    private User user;

    @OneToOne
    private Category category;

    @OneToOne
    private Login login;


    @OneToOne
    private Rate rate;


//    @OneToMany(cascade = {CascadeType.ALL})
//    @Column(unique = false,nullable = true)
//    private List<Product> products;

    public Booking() {

    }

    public Booking(Integer bookingId, String modeOfAdvance, Date deliveryDate, Float advanceAmount,
                   Float oldMetalWeight, Customer customer, Metal metal, Purity purity, Float weight, String image,
                   String catalog, Employee employee, Date bookingDate, String status, Date cancelDate, Float deductAmount,
                   Float refundAmount,
                   Integer quantity, String bookingType, User user, Category category, Login login, Rate rate) {
        this.bookingId = bookingId;
        this.modeOfAdvance = modeOfAdvance;
        this.deliveryDate = deliveryDate;
        this.advanceAmount = advanceAmount;
        this.oldMetalWeight = oldMetalWeight;
        this.customer = customer;
        this.metal = metal;
        this.purity = purity;
        this.weight = weight;
        this.image = image;
        this.catalog = catalog;
        this.employee = employee;
        this.bookingDate = bookingDate;
        this.status = status;
        this.cancelDate = cancelDate;
        this.deductAmount = deductAmount;
        this.refundAmount = refundAmount;
        this.quantity = quantity;
        this.bookingType = bookingType;
        this.user = user;
        this.category = category;
        this.login = login;
        this.rate = rate;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getModeOfAdvance() {
        return modeOfAdvance;
    }

    public void setModeOfAdvance(String modeOfAdvance) {
        this.modeOfAdvance = modeOfAdvance;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Float getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Float advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Float getOldMetalWeight() {
        return oldMetalWeight;
    }

    public void setOldMetalWeight(Float oldMetalWeight) {
        this.oldMetalWeight = oldMetalWeight;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Float getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(Float deductAmount) {
        this.deductAmount = deductAmount;
    }

    public Float getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Float refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", modeOfAdvance='" + modeOfAdvance + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", advanceAmount=" + advanceAmount +
                ", oldMetalWeight=" + oldMetalWeight +
                ", customer=" + customer +
                ", metal=" + metal +
                ", purity=" + purity +
                ", weight=" + weight +
                ", image='" + image + '\'' +
                ", catalog='" + catalog + '\'' +
                ", employee=" + employee +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                ", cancelDate=" + cancelDate +
                ", deductAmount=" + deductAmount +
                ", refundAmount=" + refundAmount +
                ", quantity=" + quantity +
                ", bookingType='" + bookingType + '\'' +
                ", user=" + user +
                ", category=" + category +
                ", login=" + login +
                ", rate=" + rate +
                '}';
    }

    //   private Login login;
    //private List<Product> products;

}


