package com.ajes.service;

import com.ajes.model.ModeOfPayment;
import com.ajes.repository.ModeOfPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeOfPaymentService {

    @Autowired
    private ModeOfPaymentRepository modeOfPaymentRepository;

    //It is post method(body)
    public ModeOfPayment addModeOfPayment(ModeOfPayment modeOfPayment){
        return modeOfPaymentRepository.save(modeOfPayment);
    }

    //It is get method(head)
    public List<ModeOfPayment> getAll(){
        return modeOfPaymentRepository.findAll();
    }

    //It is get method(head)
    public ModeOfPayment getModeOfPaymentById(Integer modeOfPaymentId){
        Optional<ModeOfPayment> optional = modeOfPaymentRepository.findById(modeOfPaymentId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public ModeOfPayment modifyModeOfPayment(Integer modeOfPaymentId,ModeOfPayment modeOfPayment){
        modeOfPayment.setModeOfPaymentId(modeOfPaymentId);
        return modeOfPaymentRepository.save(modeOfPayment);
    }



    //it is delete method(head)
    public ModeOfPayment deleteModeOfPayment(Integer modeOfPaymentId){
        ModeOfPayment modeOfPayment = getModeOfPaymentById(modeOfPaymentId);
        modeOfPaymentRepository.deleteById(modeOfPaymentId);
        return modeOfPayment;
    }


}

