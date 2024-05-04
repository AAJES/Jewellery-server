package com.ajes.controller;

import com.ajes.model.Customer;
import com.ajes.model.Employee;
import com.ajes.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
    }


    @GetMapping("/employee/")
    public ResponseEntity<List<Employee>>  getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getAll());
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable() Integer employeeId, @RequestBody() Employee employee){
        employee.setEmployeeId(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.modifyEmployee(employee));
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable() Integer employeeId){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.deleteEmployee(employeeId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable() Integer employeeId){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployeeById(employeeId));
    }





}
