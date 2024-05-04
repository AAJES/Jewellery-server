package com.ajes.controller;

import com.ajes.model.BankDetails;
import com.ajes.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class BankDetailsController {

    @Autowired
    private BankDetailsService bankDetailsService;

    @PostMapping("/bankDetails/{shopId}")
    public BankDetails addBankDetails(@PathVariable() Integer shopId,@RequestBody() BankDetails bankDetails ){

        return bankDetailsService.addBankDetails(bankDetails ,shopId);
    }

    @GetMapping("/bankDetails/")
    public List<BankDetails> getAll(){
        return bankDetailsService.getAll();
    }

    @GetMapping("/bankDetails/{bankDetailsId}")
    public BankDetails getById(@PathVariable() Integer bankDetailsId){
        return bankDetailsService.getById(bankDetailsId);
    }

    @PutMapping("/bankDetails/{bankDetailsId}")
    public BankDetails updateBankDetails(@PathVariable() Integer bankDetailsId,@RequestBody() BankDetails bankDetails){
        bankDetails.setBankDetailsId(bankDetailsId);
        return bankDetailsService.updateBankDetails(bankDetails);
    }
}
