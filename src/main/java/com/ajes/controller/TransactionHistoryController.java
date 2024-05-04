package com.ajes.controller;

import com.ajes.model.License;
import com.ajes.model.TransactionHistory;
import com.ajes.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @PostMapping("/addtransactions")
    public ResponseEntity<TransactionHistory>addTransactions(@RequestBody() TransactionHistory transactionHistory){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionHistoryService.addTransaction(transactionHistory));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<TransactionHistory>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(transactionHistoryService.getAllTransactions());
    }

    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<TransactionHistory> findByTransactionId(@PathVariable() Long transactionId){
        return  ResponseEntity.status(HttpStatus.FOUND).body(transactionHistoryService.findByTransactionId(transactionId));
    }
    @PutMapping("/transactionss/{transactionId}")
    public ResponseEntity<TransactionHistory> modifyTransaction(@PathVariable() Long transactionId, @RequestBody() TransactionHistory transactionHistory){
        transactionHistory.setTransactionId(transactionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionHistoryService.updateTransactions(transactionHistory));
    }

    @DeleteMapping("/transactionsss/{transactionId}")
    public ResponseEntity<TransactionHistory> deleteTransaction(@PathVariable() Long transactionId){
        return ResponseEntity.status(HttpStatus.FOUND).body(transactionHistoryService.deleteTransaction(transactionId));
    }




}
