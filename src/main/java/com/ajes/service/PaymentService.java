package com.ajes.service;

import com.ajes.model.*;
import com.ajes.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RateService rateService;

    @Autowired
    private MetalService metalService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;
@Autowired
private  BillService billService;


    @Autowired
    private TransactionHistoryService transactionHistoryService;

    //It is post method(body)
    public Payment addPayment(@org.jetbrains.annotations.NotNull Payment payment) {
        //  payment.setUser(null);
        //   payment.setLogin(null);

        payment.setBankDetails(null);
        System.out.println(payment.toString());

        return paymentRepository.save(payment);
    }

    //It is get method(head)
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    //It is get method(head)
    public Payment getPaymentById(Long paymentId) {
        Optional<Payment> optional = paymentRepository.findById(paymentId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    public List<Payment> getAllPaymentsOnThatDay() {
        String date11 = LocalDateTime.now().toString().substring(0, 10);


        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Payment> list = getAll();
        List<Payment> list1 = new ArrayList<>();

        try {

            for (Payment payment : list) {
                String date = payment.getTrdate().toString().substring(0, 10);

                Date date3 = dateFormat.parse(date);


//                int comparisonResult1 = date3.compareTo(date11);
//                System.out.println(comparisonResult1);
//                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date.equals(date11)) {
                    list1.add(payment);
                }

            }

            return list1;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//            Date date1 = dateFormat.parse(date11.toString());
////            Date date2 = dateFormat.parse(toDate.substring(0,10));
//            System.out.println("Converted Date: " + date1);
//            System.out.println("Converted Date: " + date2);

    }

    public List<Payment> getAllPendingPaymentsByCustomer(Integer customerId) {
        List<Payment> list = getAll();

        Customer customer = customerService.getCustomerById(customerId);

        List<Payment> list1 = new ArrayList<>();

        for (Payment p : list) {
            if (p.getCustomer().equals(customer)) {
                Float totalAmount = p.getAmountPaid() + p.getDiscountAmount();
                if (totalAmount < (p.getTotalAmount())) {
                    list1.add(p);
                }
            }
        }
        return list1;
    }

    public List<Payment> getAllPendingPayments() {
        List<Payment> list = getAll();

        List<Payment> list1 = new ArrayList<>();

        for (Payment p : list) {
            Float totalAmount = p.getAmountPaid() + p.getDiscountAmount();
            if (totalAmount < (p.getTotalAmount())) {
                list1.add(p);
            }
        }
        return list1;
    }

    public Payment getPaymentByBookingId(Integer bookingId, Payment payment) {
        //payment = paymentRepository.save(payment);
        Booking booking = bookingService.getBookingById(bookingId);


//        List<Product> productPurchaseList = booking.getProducts();
//        payment.setCustomer(booking.getCustomer());
//        List<Product> products = new ArrayList<>();
//        payment.setProducts(productPurchaseList);
//        Float netWeight = 0.0f;
//        Float grossWeight = 0.0f;
//        Float actualAmount = 0.0f;+
//        Rate rate = new Rate();
//
//        Metal metal1 = metalService.getMetalById(1);
//        Metal metal2 = metalService.getMetalById(2);
//
//        for(Product p : productPurchaseList){
//            System.out.println("Size:"+productPurchaseList.size());
//
//            for(int i = 0;i<productPurchaseList.size();i++){
//                Metal metal = p.getMetal();
//                System.out.println("Metal:"+p.getMetal());
//                products.add(p);
//
//                if(metal.equals(metal1)){
//                    String bookingDate = booking.getBookingDate().toString().substring(0,10);
//                    System.out.println("Booking Date:"+bookingDate);
//                    System.out.println("Metal Id:"+metal.getMetalId());
//                    rate = rateService.getRateByDateForGold(bookingDate,metal.getMetalId(),p.getPurity().getPurityId());
//                    System.out.println("Rate:"+rate);
//                    netWeight = netWeight + p.getWeightage();
//                    System.out.println("NetWeight:"+netWeight);
//                }
//
//                if(metal.equals(metal2)){
//                    String bookingDate = booking.getBookingDate().toString().substring(0,10);
//                    System.out.println("Booking Date:"+bookingDate);
//                    System.out.println("Metal Id:"+metal.getMetalId());
//                    rate = rateService.getRateByDateForSilver(bookingDate,metal.getMetalId());
//                    System.out.println("Rate:"+rate);
//                    netWeight = netWeight + p.getWeightage();
//                    System.out.println("NetWeight:"+netWeight);
//                }
//
//                grossWeight = grossWeight + netWeight;
//                System.out.println("GrossWeight:"+grossWeight);
//                System.out.println("Rate:"+rate.getRatePerGram());
//                actualAmount = actualAmount + grossWeight*rate.getRatePerGram();
//                System.out.println(actualAmount);
//            }
//        }


///*
////        sales.setActualAmount(actualAmount);
////        sales.setGrossWeight(grossWeight);
////        sales.setNetWeight(netWeight);
////        sales.setDiscountAmount(sales.getDiscountAmount());
////        sales.setWastage(sales.getWastage());
//*/


//        payment.setModeOfPayment(null);
//        payment.setUser(booking.getEmployee().getUser());
//        payment.setProducts(products);
//        payment.setTotalAmount(actualAmount);
//        payment.setTotalWeight(netWeight);
//        System.out.println(payment.getLogin());
//        //payment.setLogin(null);

        return paymentRepository.save(payment);
    }

    //it is put method(head and body)
    public Payment modifyPayment(Payment payment) {
        System.out.println("111111111111111111111111111");
        payment.setAmountPaid(payment.getAmountPaid() - payment.getDiscountAmount());
//        payment.getUser().setRole(payment.getUser().getRole());
//        payment.setUser(payment.getUser());
        //payment.setUser(null);
        for (Product product : payment.getProducts()) {
            System.out.println("2222222222222222222222222222");
             productService.modifyProduct(product);

        }

        Float totalAmount = payment.getAmountPaid() + payment.getDiscountAmount();

        payment.setAmountPaid(totalAmount);
        if (totalAmount.equals(payment.getTotalAmount())) {
            System.out.println("33333333333333333333333333333333333333");
            payment.setPaymentStatus(true);

            return paymentRepository.save(payment);
        }

        System.out.println("44444444444444444444444444444444444444444");

        TransactionHistory transactionHistory=new TransactionHistory();

        transactionHistory.setBill(billService.getBillByPaymentId(payment.getPaymentId()));
        transactionHistory.setPayment(payment);
        transactionHistory.setTransactionAmount(payment.getAmountPaid());
        transactionHistory.setTransactionAmount(payment.getAmountPaid());
        transactionHistoryService.addTransaction(transactionHistory);
        return paymentRepository.save(payment);
    }

    //it is delete method(head)
    public Payment deletePayment(Long paymentId) {
        Payment customer = getPaymentById(paymentId);
        paymentRepository.deleteById(paymentId);
        return customer;
    }
}
