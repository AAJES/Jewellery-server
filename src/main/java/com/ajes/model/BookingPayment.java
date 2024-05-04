package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BookingPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingPaymentId;
    @OneToOne
    private Booking booking;
    @OneToOne
   private  Product product;


    @OneToOne
    private Customer customer;

    public BookingPayment() {
    }


    public BookingPayment(Integer bookingPaymentId, Booking booking, Product product ,Customer customer) {
        this.bookingPaymentId = bookingPaymentId;
        this.booking = booking;
        this.product = product;
        this.customer=customer;
    }


    public Integer getBookingPaymentId() {
        return bookingPaymentId;
    }

    public void setBookingPaymentId(Integer bookingPaymentId) {
        this.bookingPaymentId = bookingPaymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "BookingPayment{" +
                "bookingPaymentId=" + bookingPaymentId +
                ", booking=" + booking +
                ", product=" + product +
                '}';
    }
}
