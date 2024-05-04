package com.ajes.controller;

import com.ajes.model.Quantity;
import com.ajes.service.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class QuntityController {

    @Autowired
    private QuantityService quantityService;
    @PostMapping("/addQuantity")
    public Quantity addQuantity(@RequestBody Quantity quantity){

      return   quantityService.addQuantity(quantity);
    }

    @GetMapping("/getAllQuantity")
    public List<Quantity> getAllQuantity(){
        return quantityService.getAllQuantity();
    }

        @GetMapping("/quantity/{quantityId}")
    public Optional<Quantity> getByQuantityId(@PathVariable() Integer quantityId){
        return quantityService.findByquantityId(quantityId);
    }


    @PutMapping("/editQuantity/{quantityId}")
    public ResponseEntity<?> modifyByQuantity(@RequestBody Quantity quantity, @PathVariable Integer quantityId) {
        try {
            Quantity modifiedQuantity = quantityService.modifyByQuantity(quantity, quantityId);
            return ResponseEntity.ok(modifiedQuantity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quantity not found with ID: " + quantityId);
        }
    }
}
