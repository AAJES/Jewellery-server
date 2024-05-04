package com.ajes.service;

import com.ajes.model.Quantity;
import com.ajes.repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuantityService {

    @Autowired
    private QuantityRepository quantityRepository;

    public Quantity addQuantity(Quantity quantity){

        Integer maxId = quantityRepository.findMaxQuantityId();
        int newId = (maxId == null) ? 1 : maxId + 1;

        while (quantityRepository.existsByQuantityId(newId)) {
            newId++;
        }

        quantity.setQuantityId(newId);
        return quantityRepository.save(quantity);
    }

    public List<Quantity> getAllQuantity() {
        return quantityRepository.findAll();
    }

    public Optional<Quantity> findByquantityId(Integer quantityId) {
        Optional<Quantity> quantity=quantityRepository.findById(quantityId);
        return quantity;
    }

    public Quantity modifyByQuantity(Quantity quantity,Integer quantityId ){
        Quantity quantity1=findByquantityId(quantityId).get();
        quantity1.setQuantity(quantity.getQuantity());
        quantityRepository.save(quantity1);
        return quantity1;

    }
}
