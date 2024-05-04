package com.ajes.service;

import com.ajes.model.IdForm;
import com.ajes.repository.IdFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdFormService {

    @Autowired
    private IdFormRepository idFormRepository;

    public IdForm addIdForm(IdForm idForm){
        return idFormRepository.save(idForm);
    }

    public List<IdForm> getAllIdForm(){
        return idFormRepository.findAll();
    }

    public IdForm getById(Integer idFormId){
        return idFormRepository.findById(idFormId).get();
    }

    public IdForm updateIdForm(IdForm idForm){
        return idFormRepository.save(idForm);
    }
}
