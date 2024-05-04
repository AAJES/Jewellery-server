package com.ajes.service;

import com.ajes.model.BankDetails;
import com.ajes.model.Shop;
import com.ajes.repository.BankDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDetailsService {

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    private ShopService shopService;

    public BankDetails addBankDetails(BankDetails bankDetails,Integer shopId){
        bankDetails = bankDetailsRepository.save(bankDetails);
        Shop shop = shopService.getShopById(shopId);
        shop.setBankDetails(bankDetails);
        shopService.addShop(shop);
        return bankDetails;
    }

    public List<BankDetails> getAll(){
        return bankDetailsRepository.findAll();
    }

    public BankDetails getById(Integer bankDetailsId){
        return bankDetailsRepository.findById(bankDetailsId).get();
    }

    public BankDetails updateBankDetails(BankDetails bankDetails){
        return bankDetailsRepository.save(bankDetails);
    }
}
