package com.ajes.controller;

import com.ajes.model.User;
import com.ajes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody() User user, Integer number){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user ,number));
    }

    @GetMapping("/user/")
    public ResponseEntity<List<User>> getAll(){


        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getAll());
    }

    @GetMapping("/user/userName/{userName}")
    public Optional< User> getUserByUserName(@PathVariable() String userName){
        return userService.findUserByUserName(userName);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getByUserId(@PathVariable() Long userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserById(userId));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> modifyUser(@PathVariable() Long userId,@RequestBody() User user){
        user.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.modifyUser(user));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable() Long userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.deleteUser(userId));
    }
}
