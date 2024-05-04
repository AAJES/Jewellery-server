package com.ajes.service;

import com.ajes.model.Rate;
import com.ajes.model.Return;
import com.ajes.model.Sales;
import com.ajes.repository.ReturnRepository;
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
public class ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    @Autowired
    private RateService rateService;

    //It is post method(body)
    public Return addReturn(Return returns){
        returns.setGrossWeight(returns.getNetWeight()-returns.getWastage());
        Rate rate = new Rate();
        System.out.println(returns.getMetal().getMetalName());
        String metalName = returns.getMetal().getMetalName();
        String date = LocalDateTime.now().toString().substring(0,10);
        System.out.println(date);
        if(returns.getMetal().getMetalName().equals("Gold")){
            rate = rateService.getRateByDateForGold(date,returns.getMetal().getMetalId(),returns.getPurity().getPurityId() );
            System.out.println(rate);
            returns.setReturnAmount(returns.getNetWeight()*rate.getRatePerGram());
        }
        if(returns.getMetal().getMetalName().equals("Silver")){
            rate = rateService.getRateByDateForSilver(date,returns.getMetal().getMetalId());
            System.out.println(rate);
            returns.setReturnAmount(returns.getNetWeight()*rate.getRatePerGram());
        }

        returns.setUser(null);
        return returnRepository.save(returns);
    }

    //It is get method(head)
    public List<Return> getAll(){
        return returnRepository.findAll();
    }

    public List<Return> getAllOnTodayDate(){
        String date11 = LocalDateTime.now().toString().substring(0,10);

        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Return> list = getAll();
        List<Return> list1 = new ArrayList<>();

        try {
//            Date date1 = dateFormat.parse(date11.toString());
////            Date date2 = dateFormat.parse(toDate);
//            System.out.println("Converted Date: " + date1);
//            System.out.println("Converted Date: " + date2);

            for(Return retu : list) {
                String date = retu.getTrdate().toString().substring(0,10);

                Date date3 = dateFormat.parse(date);


//                int comparisonResult1 = date3.compareTo(date11);
//                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date.equals(date11)) {
                    list1.add(retu);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }

    public List<Return> getReturnFromDateToDate(String fromDate,String toDate){
        String pattern = "yyyy-MM-dd";

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        List<Return> list = getAll();
        List<Return> list1 = new ArrayList<>();

        try {
            Date date1 = dateFormat.parse(fromDate.substring(0,10));
            Date date2 = dateFormat.parse(toDate.substring(0,10));


            for(Return retu : list) {
                String date = retu.getTrdate().toString();

                Date date3 = dateFormat.parse(date);


                int comparisonResult1 = date3.compareTo(date1);
                int comparisonResult2 = date3.compareTo(date2);

//            	  if (asset.getPurchaseDateandTime().after(subtractDays(date1, 1)) && asset.getPurchaseDateandTime().before(addDays(date2, 1))) {
//                      list1.add(asset);
//                  }

                if (date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) {
                    list1.add(retu);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception if the string does not match the pattern
        }
        return list1;
    }
    //It is get method(head)
    public Return getReturnById(Long returnId){
        Optional<Return> optional = returnRepository.findById(returnId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public Return modifyReturn(Return returns){
        return returnRepository.save(returns);
    }

    //it is delete method(head)
    public Return deleteReturn(Long returnId){
        Return returns = getReturnById(returnId);
        returnRepository.deleteById(returnId);
        return  returns;
    }
}
