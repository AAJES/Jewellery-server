package com.ajes.controller;


import com.ajes.model.Customer;
import com.ajes.model.Payment;
import com.ajes.service.PaymentService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<Payment> addPayment(@RequestBody() Payment payment){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.addPayment(payment));
    }


    @GetMapping("/payment/")
    public ResponseEntity<List<Payment>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(paymentService.getAll());
    }

    @GetMapping("/payment/getOnThatDay/")
    public List<Payment> getAllPaymentsOnThatDay(){
        return paymentService.getAllPaymentsOnThatDay();
    }

    @PostMapping("/payment/booking/{bookingId}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable() Integer bookingId,@RequestBody() Payment payment){
        return ResponseEntity.status(HttpStatus.FOUND).body(paymentService.getPaymentByBookingId(bookingId,payment));
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> getByPaymentId(@PathVariable() Long paymentId){
        return ResponseEntity.status(HttpStatus.FOUND).body(paymentService.getPaymentById(paymentId));
    }

    @GetMapping("/payment/pending/{customerId}")
    public List<Payment> getAllPendingPaymentsByCustomer(@PathVariable() Integer customerId){
        return paymentService.getAllPendingPaymentsByCustomer(customerId);
    }

    @GetMapping("/payment/pendingPayments/")
    public List<Payment> getAllPendingPayments(){
        return paymentService.getAllPendingPayments();
    }

    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> modifyPayment(@PathVariable() Long paymentId, @RequestBody() Payment payment){

        payment.setPaymentId(paymentId);
        System.out.println(payment.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.modifyPayment(payment));
    }
    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> deletePayment(@PathVariable() Long paymentId){
        return ResponseEntity.status(HttpStatus.FOUND).body(paymentService.deletePayment(paymentId));
    }






}
