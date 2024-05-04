package com.ajes.controller;


import com.ajes.model.Customer;
import com.ajes.model.Shop;
import com.ajes.service.ShopService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/shop")
    public ResponseEntity<Shop> addShop(@RequestBody() Shop shop){
        return ResponseEntity.status(HttpStatus.CREATED).body(shopService.addShop(shop));
    }


        @GetMapping("/shop/")
    public ResponseEntity<List<Shop>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(shopService.getAll());
    }


//    @GetMapping("/shop")
//    public ResponseEntity<List<Shop>> getAll() {
//        List<Shop> shops = shopService.getAll();
//        return new ResponseEntity<>(shops, HttpStatus.OK);
//    }
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<Shop> getByShopId(@PathVariable() Integer shopId){
        return ResponseEntity.status(HttpStatus.OK).body(shopService.getShopById(shopId));
    }

    @PutMapping("/shop/{shopId}")
    public ResponseEntity<Shop> modifyShop(@PathVariable() Integer shopId, @RequestBody() Shop shop){
        shop.setShopId(shopId);
        return ResponseEntity.status(HttpStatus.CREATED).body(shopService.modifyShop(shop));
    }
    @DeleteMapping("/shop/{shopId}")
    public ResponseEntity<Shop> deleteShop(@PathVariable() Integer shopId){
        return ResponseEntity.status(HttpStatus.FOUND).body(shopService.deleteShop(shopId));
    }






}
