package com.ajes.controller;

import com.ajes.model.Customer;
import com.ajes.model.License;
import com.ajes.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @PostMapping("/license")
    public ResponseEntity<License> addLicense(@RequestBody() License license){
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.addLicense(license));
    }

    @GetMapping("/license/")
    public ResponseEntity<List<License>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(licenseService.getAll());
    }

    @GetMapping("/license/{licenseId}")
    public ResponseEntity<License> getByLicenseId(@PathVariable() Integer licenseId){
        return ResponseEntity.status(HttpStatus.FOUND).body(licenseService.getLicenseById(licenseId));
    }

    @PutMapping("/license/{licenseId}")
    public ResponseEntity<License> modifyLicense(@PathVariable() Integer licenseId,@RequestBody() License license){
        license.setLicenseId(licenseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.modifyLicense(license));
    }

    @DeleteMapping("/license/{licenseId}")
    public ResponseEntity<License> deleteLicense(@PathVariable() Integer licenseId){
        return ResponseEntity.status(HttpStatus.FOUND).body(licenseService.deleteLicense(licenseId));
    }
}
