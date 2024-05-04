package com.ajes.service;

import com.ajes.model.Gender;
import com.ajes.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public Gender addGender(Gender gender){
        return genderRepository.save(gender);
    }

    public List<Gender> getAll(){
        return genderRepository.findAll();
    }

    public Gender findByGenderId(Integer genderId){
        return genderRepository.findById(genderId).get();
    }

    public Gender updateGender(Gender gender){
        return genderRepository.save(gender);
    }
}
