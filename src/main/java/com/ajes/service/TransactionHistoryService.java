package com.ajes.service;

import com.ajes.model.License;
import com.ajes.model.TransactionHistory;
import com.ajes.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;


    public TransactionHistory addTransaction(TransactionHistory transactionHistory){
       return transactionHistoryRepository.save(transactionHistory);
    }

    public List<TransactionHistory> getAllTransactions(){
        return transactionHistoryRepository.findAll();
    }

    public TransactionHistory findByTransactionId(Long transactionId){
        return  transactionHistoryRepository.findById(transactionId).get();
    }

    public TransactionHistory updateTransactions(TransactionHistory transactionHistory){
        return transactionHistoryRepository.save(transactionHistory);
    }

    public TransactionHistory deleteTransaction(Long transactionId){
        TransactionHistory transactionHistory = findByTransactionId(transactionId);
        transactionHistoryRepository.deleteById(transactionId);
        return  transactionHistory;
    }
}
