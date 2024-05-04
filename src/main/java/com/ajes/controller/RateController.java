package com.ajes.controller;

import com.ajes.model.Rate;
import com.ajes.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping("/rate")
    public ResponseEntity<Rate> addRate(@RequestBody() Rate rate) {
        System.out.println("asdfgh");
        System.out.println(rate.getUser().getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(rateService.addRate(rate));
    }

    @GetMapping("/rate/")
    public ResponseEntity<List<Rate>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(rateService.getAll());
    }

    @GetMapping("/rate/{rateId}")
    public ResponseEntity<Rate> getByRateId(@PathVariable() Long rateId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(rateService.getRateById(rateId));
    }

    @GetMapping("/rate/metal/{metalId}/purity/{purityId}")
    public ResponseEntity<Rate> getRateByDateForGold(@PathVariable() Integer metalId, @PathVariable() Integer purityId) {
        LocalDateTime date = LocalDateTime.now();
        return ResponseEntity.status(HttpStatus.FOUND).body(rateService.getRateByDateForGold(date.toString().substring(0, 10), metalId, purityId));
    }

    @GetMapping("/rate/metal/{metalId}")
    public ResponseEntity<Rate> getRateByDateForSilver(@PathVariable() Integer metalId) {
        LocalDateTime date = LocalDateTime.now();
        return ResponseEntity.status(HttpStatus.FOUND).body(rateService.getRateByDateForSilver(date.toString().substring(0, 10), metalId));
    }

    @GetMapping("/rate/today")
    public List<Rate> getRateTodayDate() {
        return rateService.getRatesForTodayDate();
    }

    @PutMapping("/rate/{rateId}")
    public ResponseEntity<Rate> modifyRate(@PathVariable() Long rateId, @RequestBody() Rate rate) {
        rate.setRateId(rateId);
        return ResponseEntity.status(HttpStatus.CREATED).body(rateService.modifyRate(rate));
    }

    @DeleteMapping("/rate/{rateId}")
    public ResponseEntity<Rate> deleteRate(@PathVariable() Long rateId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(rateService.deleteRate(rateId));
    }


    @GetMapping("rat/metal/{metalId}/purity/{purityId}")
    public Rate getRateByPurityandMetalTodays(@PathVariable() Integer metalId, @PathVariable() Integer purityId) {

        System.out.println(metalId + "..." + purityId);
        Rate rate = rateService.getRateByPurityAndMetalToday(metalId, purityId);
        return rate;
    }

    @GetMapping("rat/purity/{purityId}")
    public Rate getRateByPurity( @PathVariable() Integer purityId) {


        Rate rate = rateService.getRateByPurity( purityId);
        return rate;
    }



}
