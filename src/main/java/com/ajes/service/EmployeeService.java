package com.ajes.service;


import com.ajes.model.Employee;
import com.ajes.model.User;
import com.ajes.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    private User user = new User();


    public Employee addEmployee(Employee employee) {
        user.setUserName(employee.getPhoneNumber());
        user.setPassword(employee.getPhoneNumber());
        user = userService.addUser(user,2);
        employee.setUser(user);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }


    public Employee getEmployeeById(Integer employeeId){
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if(optional.isPresent()){
            return optional.get();
        }else {
            return null;
        }
    }

    public Employee modifyEmployee(Employee employee){
        return  employeeRepository.save(employee);
    }

    public Employee deleteEmployee(Integer employeeId){
        Employee employee=getEmployeeById(employeeId);
        employeeRepository.deleteById(employeeId);
        return employee;
    }

}
