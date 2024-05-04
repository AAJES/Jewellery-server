package com.ajes.controller;

import com.ajes.model.Metal;
import com.ajes.service.MetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class MetalController {

    @Autowired
    private MetalService metalService;

    @PostMapping("/metal")
    public ResponseEntity<Metal> addMetal(@RequestBody() Metal metal){
        return ResponseEntity.status(HttpStatus.CREATED).body(metalService.addMetal(metal));
    }

    @GetMapping("/metal/")
    public List<Metal> getAll(){
        return metalService.getAll();
    }

    @GetMapping("/metal/{metalId}")
    public ResponseEntity<Metal> getByMetalId(@PathVariable() Integer metalId){
        return ResponseEntity.status(HttpStatus.FOUND).body(metalService.getMetalById(metalId));
    }

    @PutMapping("/metal/{metalId}")
    public ResponseEntity<Metal> modifyMetal(@PathVariable() Integer metalId,@RequestBody() Metal metal){
        metal.setMetalId(metalId);
        return ResponseEntity.status(HttpStatus.CREATED).body(metalService.modifyMetal(metal));
    }

    @DeleteMapping("/metal/{metalId}")
    public ResponseEntity<Metal> deleteMetal(@PathVariable() Integer metalId){
        return ResponseEntity.status(HttpStatus.FOUND).body(metalService.deleteMetal(metalId));
    }
}
