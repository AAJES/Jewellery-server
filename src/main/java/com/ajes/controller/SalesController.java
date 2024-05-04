package com.ajes.controller;

import com.ajes.model.Sales;
import com.ajes.service.SalesService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping("/sales")
    public ResponseEntity<Sales> addSales(@RequestBody() Sales sales){
        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.addSales(sales));
    }

    @GetMapping("/sales/")
    public ResponseEntity<List<Sales>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(salesService.getAllOnTodayDate());
    }

    @GetMapping("/sales/getAll/")
    public List<Sales> getAllSales(){
         return salesService.getAll();
    }


    @GetMapping("/sales/lessPaid")
    public List<Sales> getAllLessPaid(){
        return salesService.getAllLessPaid();
    }

    @GetMapping("/sales/FromDate/{fromDate}/ToDate/{toDate}")
    public ResponseEntity<List<Sales>> getSalesFromDateToDate(@PathVariable() String fromDate,@PathVariable() String toDate){
        return ResponseEntity.status(HttpStatus.FOUND).body(salesService.getSalesFromDateToDate(fromDate,toDate));
    }

    @GetMapping("/sales/{salesId}")
    public ResponseEntity<Sales> getBySalesId(@PathVariable() Long salesId){
        return ResponseEntity.status(HttpStatus.FOUND).body(salesService.getSalesById(salesId));
    }

    @PostMapping("/sales/booking/{bookingId}")
    public ResponseEntity<Sales> getSalesByBookingId(@PathVariable() Integer bookingId,@RequestBody() Sales sales){
        return ResponseEntity.status(HttpStatus.FOUND).body(salesService.getSalesByBookingId(bookingId,sales));
    }

    @PutMapping("/sales/{salesId}")
    public ResponseEntity<Sales> modifySales(@PathVariable() Long salesId,@RequestBody() Sales sales){
        sales.setSalesId(salesId);
        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.modifySales(sales));
    }

    @DeleteMapping("/sales/{salesId}")
    public ResponseEntity<Sales> deleteSales(@PathVariable() Long salesId){
        return ResponseEntity.status(HttpStatus.FOUND).body(salesService.deleteSales(salesId));
    }






}
