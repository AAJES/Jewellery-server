package com.ajes.controller;

import com.ajes.model.Customer;
import com.ajes.model.Owner;
import com.ajes.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/owner")
    public ResponseEntity<Owner> addOwner(@RequestBody() Owner owner){
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.addOwner(owner));
    }

    @GetMapping("/owner/")
    public ResponseEntity<List<Owner>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getAll());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Owner> getByOwnerId(@PathVariable() Integer ownerId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ownerService.getOwnerById(ownerId));
    }


    @PutMapping("/owner/{ownerId}")
    public ResponseEntity<Owner> modifyOwner(@PathVariable() Integer ownerId,@RequestBody() Owner owner){
        owner.setOwnerId(ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.modifyOwner(owner));
    }

    @DeleteMapping("/owner/{ownerId}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable() Integer ownerId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ownerService.deleteOwner(ownerId));
    }

    @GetMapping("/owner/phone/{phone}")
    public ResponseEntity<Owner> findByPhone(@PathVariable() String phone){
        Owner owner=ownerService.findByPhone(phone);
        return   ResponseEntity.status(HttpStatus.FOUND).body(owner);
    }

    @GetMapping("/owner/user/phone/{phone}")
    public ResponseEntity<Owner> getOwnerByUserName(@PathVariable("phone") String UserName){
        Owner owner=ownerService.getOwnerByUserName(UserName);
        return   ResponseEntity.status(HttpStatus.OK).body(owner);
    }


}
