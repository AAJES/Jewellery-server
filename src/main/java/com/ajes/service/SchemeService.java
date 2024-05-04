package com.ajes.service;

import com.ajes.model.Scheme;
import com.ajes.repository.SchemeRepository;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SchemeService {

    @Autowired
    private SchemeRepository schemeRepository;

    public  Scheme addScheme(Scheme scheme){




        Date startDate = scheme.getStartDate();
        Date endDate = scheme.getEndDate();


        long time1 = startDate.getTime();
        long time2 = endDate.getTime();
        System.out.println(time1);
        // Calculate the difference in milliseconds
        long diff = Math.abs(time1 - time2);

        // Approximate number of milliseconds in a month (30.44 days)
        double millisecondsInMonth = 30.44 * 24 * 60 * 60 * 1000;

        // Calculate the number of months
        int months = (int) (diff / millisecondsInMonth);
        scheme.setSchemeAmount( scheme.getAmountPerMonth()*months );

        List<Scheme> existingSchemes = schemeRepository.findBySchemeName(scheme.getSchemeName());
        if (!existingSchemes.isEmpty()) {

            throw new RuntimeException("Scheme Name Already Present");
        }

        // Save the new scheme
        return schemeRepository.save(scheme);
    }
    public List<Scheme> getAll(){
        return schemeRepository.findAll();
    }

    public Scheme getSchemeById(Integer schemeId){
        return schemeRepository.findById(schemeId).get();
    }

    public Scheme deleteScheme(Integer schemeId){
        Scheme scheme = schemeRepository.findById(schemeId).get();
        schemeRepository.deleteById(schemeId);
        return scheme;
    }

    public Scheme updateScheme(Scheme scheme){
//        if(scheme.getCustomers().size()<=10) {
//            return schemeRepository.save(scheme);
//        }
        return null;
    }
//    public Optional<Scheme> getBySchemeName(String schemeName){
//       Optional< Scheme> scheme=schemeRepository.findBySchemeName(schemeName);
//        return scheme;
//    }



}
