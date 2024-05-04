package com.ajes.service;

import com.ajes.model.Role;
import com.ajes.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    //It is post method(body)
    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    //It is get method(head)
    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    //It is get method(head)
    public Role getRoleById(Integer roleId){

        List<Role> list = getAll();

        if(list.isEmpty()){
            Role role = new Role();
            role.setRoleName("admin");
            role.setActiveStatus(true);
            return addRole(role);
        }else {
            Optional<Role> optional = roleRepository.findById(roleId);
            if (optional.isPresent()) {
                return optional.get();
            } else
                return null;
        }
    }

    //it is put method(head and body)
    public Role modifyRole(Role role){
        return roleRepository.save(role);
    }

    //it is delete method(head)
    public Role deleteRole(Integer roleId){
        Role role = getRoleById(roleId);
        roleRepository.deleteById(roleId);
        return  role;
    }
}
