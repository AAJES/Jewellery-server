package com.ajes.service;

import com.ajes.model.Customer;
import com.ajes.model.Owner;
import com.ajes.model.User;
import com.ajes.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserService userService;

    //It is post method(body)
    public Owner addOwner(Owner owner) {
        //Owner owner1 = ownerRepository.save(owner);

        User user = new User();
        user.setUserName(owner.getPhone());
        user.setPassword(owner.getPassword());
        user.setRole(null);
        user = userService.addUser(user, 1);
        owner.setUser(user);
        return ownerRepository.save(owner);
    }

    //It is get method(head)
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    //It is get method(head)
    public Owner getOwnerById(Integer ownerId) {
        Optional<Owner> optional = ownerRepository.findById(ownerId);
        if (optional.isPresent()) {
            return optional.get();
        } else
            return null;
    }

    //it is put method(head and body)
    public Owner modifyOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    //it is delete method(head)
    public Owner deleteOwner(Integer ownerId) {
        Owner owner = getOwnerById(ownerId);
        ownerRepository.deleteById(ownerId);
        return owner;
    }


    public Owner findByPhone(String phone) {
        System.out.println(".");
        Owner owner = ownerRepository.findByPhone(phone);
        System.out.println("..");
        return owner;
    }


    public Owner getOwnerByUserName(String UserName) {

        List<Owner> OwnerList = ownerRepository.findAll();
        User user = userService.findUserByUserName(UserName).get();
        for(Owner owner:OwnerList){
            if(owner.getUser().equals(user)){
                return  owner;
            }
        }

         return null;

    }
}
