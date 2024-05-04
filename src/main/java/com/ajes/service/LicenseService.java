package com.ajes.service;

import com.ajes.model.Customer;
import com.ajes.model.License;
import com.ajes.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    //It is post method(body)
    public License addLicense(License license){
        return licenseRepository.save(license);
    }

    //It is get method(head)
    public List<License> getAll(){
        return licenseRepository.findAll();
    }

    //It is get method(head)
    public License getLicenseById(Integer licenseId){
        Optional<License> optional = licenseRepository.findById(licenseId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public License modifyLicense(License license){
        return licenseRepository.save(license);
    }

    //it is delete method(head)
    public License deleteLicense(Integer licenseId){
        License license = getLicenseById(licenseId);
        licenseRepository.deleteById(licenseId);
        return  license;
    }
}
