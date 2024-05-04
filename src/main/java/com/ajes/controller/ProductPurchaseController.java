package com.ajes.controller;

import com.ajes.model.Customer;
import com.ajes.model.ProductPurchase;
import com.ajes.service.ProductPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class ProductPurchaseController {

    @Autowired
    private ProductPurchaseService productPurchaseService;

    @PostMapping("productpurchase")
    public ResponseEntity<ProductPurchase> addProductPurchase(@RequestBody() ProductPurchase productPurchase){
        return ResponseEntity.status(HttpStatus.CREATED).body(productPurchaseService.addProductPurchase(productPurchase));
    }

    @GetMapping("/productpurchase/")
    public ResponseEntity<List<ProductPurchase>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(productPurchaseService.getAll());
    }

    @GetMapping("/productpurchase/all")
    public ResponseEntity<List<ProductPurchase>> getAllOnTodayDate(){
        return ResponseEntity.status(HttpStatus.FOUND).body(productPurchaseService.getAllOnTodayDate());
    }

    @GetMapping("/productpurchase/FromDate/{fromDate}/ToDate/{toDate}")
    public ResponseEntity<List<ProductPurchase>> getAllProductPurchaseFromDateToDate(@PathVariable() String fromDate,@PathVariable() String toDate){
        return ResponseEntity.status(HttpStatus.FOUND).body(productPurchaseService.getFromDateAndToDate(fromDate,toDate));
    }

    @GetMapping("/productpurchase/{productPurchaseId}")
    public ResponseEntity<ProductPurchase> getByProductPurchaseId(@PathVariable() Long productPurchaseId){
        return ResponseEntity.status(HttpStatus.FOUND).body(productPurchaseService.getProductPurchaseById(productPurchaseId));
    }

    @PutMapping("/productpurchase/{productPurchaseId}")
    public ResponseEntity<ProductPurchase> modifyProductPurchase(@PathVariable() Long productPurchaseId,@RequestBody() ProductPurchase productPurchase){
        productPurchase.setProductPurchaseId(productPurchaseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productPurchaseService.modifyProductPurchase(productPurchase));
    }

    @DeleteMapping("/productpurchase/{productPurchaseId}")
    public ResponseEntity<ProductPurchase> deleteProductPurchase(@PathVariable() Long productPurchaseId){
        return ResponseEntity.status(HttpStatus.FOUND).body(productPurchaseService.deleteProductPurchase(productPurchaseId));
    }
}
