package com.ajes.controller;

import com.ajes.model.Scheme;
import com.ajes.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class SchemeController {

    @Autowired
    private SchemeService schemeService;

    @PostMapping("/scheme")
    public ResponseEntity<?> addScheme(@RequestBody Scheme scheme) {
        try {
            Scheme savedScheme = schemeService.addScheme(scheme);
            return new ResponseEntity<>(savedScheme, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Scheme Name Already Present", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/scheme/")
    public List<Scheme> getAll(){
        return schemeService.getAll();
    }

    @GetMapping("/scheme/{schemeId}")
    public Scheme getSchemeById(@PathVariable() Integer schemeId){
        return schemeService.getSchemeById(schemeId);
    }

    @DeleteMapping("/scheme/{schemeId}")
    public Scheme deleteSchemeById(Integer schemeId){
        return schemeService.deleteScheme(schemeId);
    }

    @PutMapping("/scheme/{schemeId}")
    public Scheme updateScheme(@PathVariable() Integer schemeId,@RequestBody() Scheme scheme){
        scheme.setSchemeId(schemeId);
        return schemeService.updateScheme(scheme);
    }


//    @GetMapping("/scheme/schemeName/{schemeName}")
//    public Optional<Scheme> getBySchemeName(@PathVariable() String schemeName){
//        return schemeService.getBySchemeName(schemeName);
//    }

}
