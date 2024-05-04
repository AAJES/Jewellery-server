package com.ajes.service;

import com.ajes.model.*;
import com.ajes.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MetalService metalService;

    @Autowired
    private PurityService purityService;

    @Autowired
    private RoleService roleService;

    //It is post method(body)
    public Rate addRate(Rate rate) {
//        //rate.setUser(null);
//        if (rate.getMetal().getMetalName().equals("Silver")) {
//            rate.setPurity(null);
//        }
//        List<Role> list = (List<Role>) rate.getUser().getRole();
//        System.out.println(list);
//        List<Role> list1 = new ArrayList<>();
//        for(Role r : list){
//            list1.add(r);
//            rate.getUser().setRole(list1);
//        }
        rate.setUser(null);
        return rateRepository.save(rate);
    }

    //It is get method(head)
    public List<Rate> getAll() {
        return rateRepository.findAll();
    }

    //It is get method(head)
    public Rate getRateById(Long rateId) {
        Optional<Rate> optional = rateRepository.findById(rateId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }


    public Rate getRateByDateForGold(String date, Integer metalId, Integer purityId) {

        System.out.println(date);
        Metal metal = metalService.getMetalById(metalId);
        Purity purity = purityService.getPurityById(purityId);

        List<Rate> list = getAll();

        for (Rate r : list) {

            String date1 = r.getTrDate().toString().substring(0, 10);


            if (date.equals(date1)) {
                if (metal.equals(r.getMetal())) {
                    if (purity.equals(r.getPurity())) {
                        return r;
                    }
                }
            }


        }

        return null;
    }

    public Rate getRateByDateForSilver(String date, Integer metalId) {

        Metal metal = metalService.getMetalById(metalId);

        List<Rate> list = getAll();

        for (Rate r : list) {

            String date1 = r.getTrDate().toString().substring(0, 10);

            System.out.println(date1);

            if (date.equals(date1)) {
                System.out.println("Date Match");
                if (metal.equals(r.getMetal())) {
                    System.out.println("Metal Match");
                    return r;
                }
            }
        }

        return null;
    }

    public List<Rate> getRatesForTodayDate() {
        List<Rate> list = getAll();
        String date = LocalDateTime.now().toString().substring(0, 10);

        List<Rate> list1 = new ArrayList<>();

        for (Rate r : list) {
            String date11 = r.getTrDate().toString().substring(0, 10);

            if (date11.equals(date)) {
                list1.add(r);
            } else {

                r.setActiveStatus(false);
                rateRepository.save(r);
            }
        }
        return list1;
    }

    //it is put method(head and body)
    public Rate modifyRate(Rate rate) {
        return rateRepository.save(rate);
    }

    //it is delete method(head)
    public Rate deleteRate(Long rateId) {
        Rate rate = getRateById(rateId);
        rateRepository.deleteById(rateId);
        return rate;
    }


    public Rate getRateByPurity(Integer purityId) {
        LocalDate toDate = LocalDate.now();
        Purity purity = purityService.getPurityById(purityId);
        List<Rate> list = getAll();
        for (Rate rate : list) {
            Date trDate = rate.getTrDate(); // Assuming getTrDate() returns a Date
            LocalDate trLocalDate = trDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (trLocalDate == toDate && rate.getPurity().equals(purity)) {

                System.out.println(".............................");
                return rate;
            }


        }
        return null;
    }

    public Rate getRateByPurityAndMetalToday(Integer metalId, Integer purityId) {

        LocalDate toDate = LocalDate.now();
        Rate rate1 = null;


        Purity purity = purityService.getPurityById(purityId);


        Metal metal = metalService.getMetalById(metalId);


        List<Rate> allRate = getRatesForTodayDate();

        for (Rate rate : allRate) {
            Date trDate = rate.getTrDate();
            LocalDate trLocalDate = trDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (trLocalDate.equals(toDate)) {
                rate1 = rate;
                if (rate1.getMetal().equals(metal)) {

                    Purity purity1 = new Purity();

                    purity1 = rate1.getPurity();


                    if ( purity.equals(purity1)) {

                        return rate1;

                    }
                }


            }
        }

        return null;
    }


}
