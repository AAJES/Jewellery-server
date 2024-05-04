package com.ajes.service;

import com.ajes.model.BookingPayment;
import com.ajes.model.Product;

import com.ajes.repository.BookingPaymentRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingPaymentService {

    @Autowired
    private BookingPaymentRepository bookingPaymentRepository;
    @Autowired
    private ProductService productService;


    public BookingPayment addBookingPayment(BookingPayment bookingPayment){
        return   bookingPaymentRepository.save(bookingPayment);

    }
    public BookingPayment getBookingByProductId(Long productId) {
        List<BookingPayment> bookingPaymentList = bookingPaymentRepository.findAll();
        Product product = productService.getProductById(productId);

        for (BookingPayment bookingPayment : bookingPaymentList) {
            if (bookingPayment.getProduct().equals(product)) {
                return bookingPayment;
            }
        }
        return null;
    }

}
