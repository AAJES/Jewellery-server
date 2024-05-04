package com.ajes.controller;

import com.ajes.model.Purity;
import com.ajes.service.PurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class PurityController {

    @Autowired
    private PurityService purityService;

    @PostMapping("/purity")
    public ResponseEntity<Purity> addPurity(@RequestBody() Purity purity){
        return ResponseEntity.status(HttpStatus.CREATED).body(purityService.addPurity(purity));
    }

    @GetMapping("/purity/")
    public List<Purity> getAll(){
        return purityService.getAll();
    }

    @GetMapping("/purity/{purityId}")
    public ResponseEntity<Purity> getByPurityId(@PathVariable() Integer purityId){
        return ResponseEntity.status(HttpStatus.FOUND).body(purityService.getPurityById(purityId));
    }

    @PutMapping("/purity/{purityId}")
    public ResponseEntity<Purity> modifyPurity(@PathVariable() Integer purityId,@RequestBody() Purity purity){
        purity.setPurityId(purityId);
        return ResponseEntity.status(HttpStatus.CREATED).body(purityService.modifyPurity(purity));
    }

    @DeleteMapping("/purity/{purityId}")
    public ResponseEntity<Purity> deletePurity(@PathVariable() Integer purityId){
        return ResponseEntity.status(HttpStatus.FOUND).body(purityService.deletePurity(purityId));
    }
}
