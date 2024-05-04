package com.ajes.service;

import com.ajes.model.Customer;
import com.ajes.model.User;
import com.ajes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    private User user = new User();

    //It is post method(body)
    public Customer addCustomer(Customer customer){
        user.setUserName(customer.getPhone1());
        user.setPassword(customer.getPhone2());
        user = userService.addUser(user,3);
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    //It is get method(head)
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    //It is get method(head)
    public Customer getCustomerById(Integer customerId){
        Optional<Customer> optional = customerRepository.findById(customerId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public Customer modifyCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    //it is delete method(head)
    public Customer deleteCustomer(Integer customerId){
        Customer customer = getCustomerById(customerId);
        customerRepository.deleteById(customerId);
        return  customer;
    }
}
