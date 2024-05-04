package com.ajes.controller;

import com.ajes.model.Role;
import com.ajes.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<Role> addRole(@RequestBody() Role role){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.addRole(role));
    }

    @GetMapping("/role/")
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(roleService.getAll());
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<Role> getByRoleId(@PathVariable() Integer roleId){
        return ResponseEntity.status(HttpStatus.FOUND).body(roleService.getRoleById(roleId));
    }

    @PutMapping("/role/{roleId}")
    public ResponseEntity<Role> modifyRole(@PathVariable() Integer roleId,@RequestBody() Role role){
        role.setRoleId(roleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.modifyRole(role));
    }

    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<Role> deleteRole(@PathVariable() Integer roleId){
        return ResponseEntity.status(HttpStatus.FOUND).body(roleService.deleteRole(roleId));
    }
}
