package com.ajes.service;

import com.ajes.model.*;
import com.ajes.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RateService rateService;

    @Autowired
    private MetalService metalService;

    //It is post method(body)
    public Sales addSales(Sales sales){
//        sales.setUser(null);
        sales.setBillGeneratedOrNo(true);
        sales.setGrossWeight(sales.getNetWeight()-sales.getWastage());
//        sales.setBill(null);
        return salesRepository.save(sales);
    }

    //It is get method(head)
    public List<Sales> getAll(){
        return salesRepository.findAll();
    }

    public List<Sales> getAllLessPaid(){
        List<Sales> list = getAll();

        List<Sales> list2 = new ArrayList<>();

        for(Sales s : list){
            if(s.getAmountPaid()<s.getActualAmount()){
                list2.add(s);
            }
        }
        return list2;

    }

    public List<Sales> getAllOnTodayDate(){
        String date11 = LocalDateTime.now().toString().substring(0,10);

        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Sales> list = getAll();
        List<Sales> list1 = new ArrayList<>();

        try {
//            Date date1 = dateFormat.parse(date11.toString());
////            Date date2 = dateFormat.parse(toDate.substring(0,10));
//            System.out.println("Converted Date: " + date1);
//            System.out.println("Converted Date: " + date2);

            for(Sales sales : list) {
                String date = sales.getTrdate().toString().substring(0,10);
                Date date3 = dateFormat.parse(date);

                //int comparisonResult1 = date3.compareTo(date11);
//                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

//                if (date3.equals(date11)) {
//                    list1.add(sales);
//                }
                if(date11.equals(date)){
                    list1.add(sales);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }

    //It is get method(head)
    public Sales getSalesById(Long salesId){
        Optional<Sales> optional = salesRepository.findById(salesId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    public Sales getSalesByBookingId(Integer bookingId,Sales sales){
        //sales = salesRepository.save(sales);
        Booking booking = bookingService.getBookingById(bookingId);

//        List<Product> productPurchaseList = booking.getProducts();
//        Float netWeight = 0.0f;
//        Float grossWeight = 0.0f;
//        Float actualAmount = 0.0f;
//        Rate rate = new Rate();
//
//        for(Product p : productPurchaseList){
//
//            for(int i = 0;i<productPurchaseList.size();i++){
//                Metal metal = p.getMetal();
//
//                if(metal.getMetalName().equals("Gold")){
//                     rate = rateService.getRateByDateForGold(booking.getBookingDate().toString(),metal.getMetalId(),p.getPurity().getPurityId());
//                     netWeight = netWeight + p.getWeightage();
//                }
//
//                if(metal.getMetalName().equals("Silver")){
//                     rate = rateService.getRateByDateForSilver(booking.getBookingDate().toString(),metal.getMetalId());
//                     netWeight = netWeight + p.getWeightage();
//                }
//
//                grossWeight = grossWeight + netWeight;
//                actualAmount = actualAmount + grossWeight*rate.getRatePerGram() - sales.getDiscountAmount();
//            }
//        }

//        sales.setActualAmount(actualAmount);
//        sales.setGrossWeight(grossWeight);
//        sales.setNetWeight(netWeight);
//        sales.setDiscountAmount(sales.getDiscountAmount());
//        sales.setWastage(sales.getWastage());

        return salesRepository.save(sales);
    }

    public List<Sales> getSalesFromDateToDate(String fromDate,String toDate){
        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Sales> list = getAll();
        List<Sales> list1 = new ArrayList<>();

        try {
            Date date1 = dateFormat.parse(fromDate.substring(0,10));
            Date date2 = dateFormat.parse(toDate.substring(0,10));

            for(Sales sales : list) {
                String date = sales.getTrdate().toString();
                Date date3 = dateFormat.parse(date);

                int comparisonResult1 = date3.compareTo(date1);
                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                    list1.add(sales);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }

    //it is put method(head and body)
    public Sales modifySales(Sales sales){
        return salesRepository.save(sales);
    }

    //it is delete method(head)
    public Sales deleteSales(Long salesId){
        Sales bill = getSalesById(salesId);
        salesRepository.deleteById(salesId);
        return  bill;
    }
}
