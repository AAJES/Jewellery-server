package com.ajes.service;

import com.ajes.model.Purity;
import com.ajes.repository.PurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurityService {

    @Autowired
    private PurityRepository purityRepository;

    //It is post method(body)
    public Purity addPurity(Purity purity){
        return purityRepository.save(purity);
    }

    //It is get method(head)
    public List<Purity> getAll(){
        return purityRepository.findAll();
    }

    //It is get method(head)
    public Purity getPurityById(Integer purityId){
        Optional<Purity> optional = purityRepository.findById(purityId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public Purity modifyPurity(Purity purity){
        return purityRepository.save(purity);
    }

    //it is delete method(head)
    public Purity deletePurity(Integer purityId){
        Purity purity = getPurityById(purityId);
        purityRepository.deleteById(purityId);
        return  purity;
    }




}
