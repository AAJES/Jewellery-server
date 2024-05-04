package com.ajes.service;

import com.ajes.model.Customer;
import com.ajes.model.Metal;
import com.ajes.repository.MetalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetalService {

    @Autowired
    private MetalRepository metalRepository;

    //It is post method(body)
    public Metal addMetal(Metal metal){
        return metalRepository.save(metal);
    }

    //It is get method(head)
    public List<Metal> getAll(){
        return metalRepository.findAll();
    }

    //It is get method(head)
    public Metal getMetalById(Integer metalId){
        Optional<Metal> optional = metalRepository.findById(metalId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public Metal modifyMetal(Metal metal){
        return metalRepository.save(metal);
    }

    //it is delete method(head)
    public Metal deleteMetal(Integer metalId){
        Metal metal = getMetalById(metalId);
        metalRepository.deleteById(metalId);
        return  metal;
    }
}
