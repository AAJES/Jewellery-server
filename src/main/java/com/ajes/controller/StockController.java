package com.ajes.controller;

import com.ajes.model.Customer;
import com.ajes.model.Stock;
import com.ajes.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/stock")
    public ResponseEntity<Stock> addStock(@RequestBody() Stock stock){
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.addStock(stock));
    }

    @GetMapping("/stock/")
    public ResponseEntity<List<Stock>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAll());
    }

    @GetMapping("/stock/product/{productId}")
    public Stock getStockByProduct(@PathVariable() Long productId){
        return stockService.getStockByProduct(productId);
    }

        @GetMapping("/stock/FromDate/{fromDate}/ToDate/{toDate}")
    public ResponseEntity<List<Stock>> getStockFromDateToDate(@PathVariable() String fromDate,@PathVariable() String toDate){
        return ResponseEntity.status(HttpStatus.FOUND).body(stockService.getStockFromDateToDate(fromDate,toDate));
    }

    @GetMapping("/stock/{stockId}")
    public ResponseEntity<Stock> getByStockId(@PathVariable() Long stockId){
        return ResponseEntity.status(HttpStatus.FOUND).body(stockService.getStockById(stockId));
    }

    @PutMapping("/stock/{stockId}")
    public ResponseEntity<Stock> modifyStock(@PathVariable() Long stockId,@RequestBody() Stock stock )   {
        int q=0;
        stock.setStockId(stockId);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.modifyStock(stock,q));
    }

    @DeleteMapping("/stock/{stockId}")
    public ResponseEntity<Stock> deleteStock(@PathVariable() Long stockId){
        return ResponseEntity.status(HttpStatus.FOUND).body(stockService.deleteStock(stockId));
    }



}
