package com.ajes.controller;


import com.ajes.model.Customer;
import com.ajes.model.ModeOfPayment;
import com.ajes.service.ModeOfPaymentService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class ModeOfPaymentController {

    @Autowired
    private ModeOfPaymentService modeOfPaymentService;

    @PostMapping("/modeOfPayment")
    public ResponseEntity<ModeOfPayment> addModeOfPayment(@RequestBody() ModeOfPayment modeOfPayment){
        return ResponseEntity.status(HttpStatus.CREATED).body(modeOfPaymentService.addModeOfPayment(modeOfPayment));
    }


    @GetMapping("/modeOfPayment/")
    public ResponseEntity<List<ModeOfPayment>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(modeOfPaymentService.getAll());
    }
    @GetMapping("/modeOfPayment/{modeOfPaymentId}")
    public ResponseEntity<ModeOfPayment> getByModeOfPaymentId(@PathVariable() Integer modeOfPaymentId){
        return ResponseEntity.status(HttpStatus.FOUND).body(modeOfPaymentService.getModeOfPaymentById(modeOfPaymentId));
    }

    @PutMapping("/modeOfPayment/{modeOfPaymentId}")
    public ResponseEntity<ModeOfPayment> modifyModeOfPayment(@PathVariable() Integer modeOfPaymentId, @RequestBody() ModeOfPayment modeOfPayment){
        //modeOfPayment.setModeOfPaymentId(modeOfPaymentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(modeOfPaymentService.modifyModeOfPayment(modeOfPaymentId,modeOfPayment));
    }






    @DeleteMapping("/modeOfPayment/{modeOfPaymentId}")
    public ResponseEntity<ModeOfPayment> deleteModeOfPayment(@PathVariable() Integer modeOfPaymentId){
        return ResponseEntity.status(HttpStatus.FOUND).body(modeOfPaymentService.deleteModeOfPayment(modeOfPaymentId));
    }






}
