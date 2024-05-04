package com.ajes.service;

import com.ajes.model.Shop;
import com.ajes.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    //It is post method(body)
    public Shop addShop(Shop shop){

        return shopRepository.save(shop);
    }

    //It is get method(head)
    public List<Shop> getAll(){
        return shopRepository.findAll();
    }

    //It is get method(head)
    public Shop getShopById(Integer shopId){
        Optional<Shop> optional = shopRepository.findById(shopId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public Shop modifyShop(Shop shop){
        return shopRepository.save(shop);
    }

    //it is delete method(head)
    public Shop deleteShop(Integer shopId){
        Shop shop = getShopById(shopId);
        shopRepository.deleteById(shopId);
        return shop;
    }
}

