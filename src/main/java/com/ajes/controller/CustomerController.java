package com.ajes.controller;

import com.ajes.model.Customer;
import com.ajes.service.CustomerService;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody() Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    @GetMapping("/customer/")
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.getAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getByCustomerId(@PathVariable() Integer customerId){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.getCustomerById(customerId));
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<Customer> modifyCustomer(@PathVariable() Integer customerId,@RequestBody() Customer customer){
        customer.setCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.modifyCustomer(customer));
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable() Integer customerId){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.deleteCustomer(customerId));
    }






}
