package com.ajes.controller;

import com.ajes.model.Login;
import com.ajes.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Login> addLogin(@RequestBody() Login login){
        return ResponseEntity.status(HttpStatus.CREATED).body(loginService.addLogin(login));
    }

    @GetMapping("/get-client-ip")
    public String getClientIP(HttpServletRequest request) {
        String clientIP = request.getRemoteAddr(); // Obtain the client's IP address
        return clientIP;
    }

    @GetMapping("/login/")
    public ResponseEntity<List<Login>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(loginService.getAll());
    }

    @GetMapping("/login/user/{userId}")
    public Login getLoginByUser(@PathVariable() Long userId){
        return loginService.getLoginByUser(userId);
    }

    @GetMapping("/login/{loginId}")
    public ResponseEntity<Login> getByLoginId(@PathVariable() Long loginId){
        return ResponseEntity.status(HttpStatus.FOUND).body(loginService.getLoginById(loginId));
    }

    @PutMapping("/login/{loginId}")
    public ResponseEntity<Login> modifyLogin(@PathVariable() Long loginId,@RequestBody() Login login){
        login.setLoginId(loginId);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginService.modifyLogin(login));
    }

    @DeleteMapping("/login/{loginId}")
    public ResponseEntity<Login> deleteLogin(@PathVariable() Long loginId){
        return ResponseEntity.status(HttpStatus.FOUND).body(loginService.deleteLogin(loginId));
    }
}
