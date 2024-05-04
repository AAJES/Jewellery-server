package com.ajes.controller;

import com.ajes.model.Return;
import com.ajes.service.ReturnService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @PostMapping("/return")
    public ResponseEntity<Return> addReturn(@RequestBody() Return returns){
        return ResponseEntity.status(HttpStatus.CREATED).body(returnService.addReturn(returns));
    }

    @GetMapping("/return/")
    public ResponseEntity<List<Return>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(returnService.getAllOnTodayDate());
    }

    @GetMapping("/return/getAll")
    public List<Return> getAllReturns(){
        return returnService.getAll();
    }

    @GetMapping("/return/FromDate/{fromDate}/ToDate/{toDate}")
    public ResponseEntity<List<Return>> getReturnFromDateToDate(@PathVariable() String fromDate,@PathVariable() String toDate){
        return ResponseEntity.status(HttpStatus.OK).body(returnService.getReturnFromDateToDate(fromDate,toDate));
    }


    @GetMapping("/return/{returnId}")
    public ResponseEntity<Return> getByReturnId(@PathVariable() Long returnId){
        return ResponseEntity.status(HttpStatus.FOUND).body(returnService.getReturnById(returnId));
    }

    @PutMapping("/return/{returnId}")
    public ResponseEntity<Return> modifyReturn(@PathVariable() Long returnId,@RequestBody() Return returns){
        returns.setReturnId(returnId);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnService.modifyReturn(returns));
    }

    @DeleteMapping("/return/{returnId}")
    public ResponseEntity<Return> deleteReturn(@PathVariable() Long returnId){
        return ResponseEntity.status(HttpStatus.FOUND).body(returnService.deleteReturn(returnId));
    }






}
