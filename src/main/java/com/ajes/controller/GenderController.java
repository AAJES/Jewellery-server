package com.ajes.controller;

import com.ajes.model.Gender;
import com.ajes.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @PostMapping("/gender")
    public ResponseEntity<Gender> addGender(@RequestBody() Gender gender){
        return ResponseEntity.status(HttpStatus.CREATED).body(genderService.addGender(gender));
    }

    @GetMapping("/gender/")
    public ResponseEntity<List<Gender>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(genderService.getAll());
    }

    @GetMapping("/gender/{genderId}")
    public ResponseEntity<Gender> findByGenderId(@PathVariable() Integer genderId){
        return ResponseEntity.status(HttpStatus.FOUND).body(genderService.findByGenderId(genderId));
    }

    @PutMapping("/gender/{genderId}")
    public ResponseEntity<Gender> updateGender(@PathVariable() Integer genderId,@RequestBody() Gender gender){
        gender.setGenderId(genderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(genderService.updateGender(gender));
    }
}
