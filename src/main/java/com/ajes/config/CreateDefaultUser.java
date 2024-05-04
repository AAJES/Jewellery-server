package com.ajes.config;

import com.ajes.model.*;
import com.ajes.repository.ShopRepository;
import com.ajes.service.BankDetailsService;
import com.ajes.service.OwnerService;
import com.ajes.service.ShopService;
import com.ajes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CreateDefaultUser {


    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopRepository shopRepository;

    @Autowired

    private UserService userService;
    @Autowired
    private BankDetailsService bankDetailsService;

    @Autowired
    private OwnerService ownerService;
    Integer id;
    Integer number;

    public User createDefaultUser() {
        User user1 = new User();
        user1.setUserName("Admin");
        user1.setPassword("123@Admin");
        user1.setActiveStatus(true);
        Shop shop = createDefaultShop();
        Role role = createDefaultRole();
        user1.setRole(Collections.singletonList(role));
        System.out.println(user1.toString());
        userService.addUser(user1, number);
        return user1;
    }


    private Role createDefaultRole() {
        Role role = new Role();
        role.setRoleName("Admin");
        number = role.getRoleId();
        return role;

    }


    private Shop createDefaultShop() {
        Shop shop = new Shop();
        shop.setAddress("hubli");
        shop.setEmail("ajeswebsite@gmail.com");
        shop.setLogo("NA");
        shop.setGstNo("KA123456H");
        shop.setState("Karnataka");
        shop.setShopName("AJES");
        shopRepository.save(shop);
        Owner owner = createDefaultOwner();
        BankDetails bankDetails = createDefaultBankDetails();
        shop.setBankDetails(bankDetails);
      shopRepository.save(shop);
        id = shop.getShopId();
        shopRepository.save(shop);
        return shop;
    }

    private Owner createDefaultOwner() {
        Owner owner = new Owner();
        owner.setOwnerName("Admin");
        owner.setPassword("123@Admin");  //this is password
        owner.setPhone("9886642564");  //this is username
        ownerService.addOwner(owner);
        return owner;
    }

    private BankDetails createDefaultBankDetails() {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setDescription("admin");
        bankDetails.setAccountNo("9886642564");
        bankDetails.setActive(true);
        bankDetails.setBranch("hubli");
        bankDetails.setAccountHolderName("ajes");
        bankDetails.setIFSCCode("123");
        bankDetails.setBranch("hubli");
        bankDetailsService.addBankDetails(bankDetails, id);
        return bankDetails;
    }


}


