package com.ajes.service;

import com.ajes.config.CreateDefaultUser;
import com.ajes.model.Role;
import com.ajes.model.User;
import com.ajes.repository.UserRepository;
import com.ajes.security.UserServiceSecurityInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserServiceSecurityInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    @Lazy
    private CreateDefaultUser createDefaultUser;

    //It is post method(body)
    public User addUser(User user,Integer number){
//        if(number == 1){
//            user.setRole((Collection<Role>) roleService.getRoleById(number));
//        } else if (number == 2) {
//            user.setRole((Collection<Role>) roleService.getRoleById(number));
//        } else if (number == 3) {
//            user.setRole((Collection<Role>) roleService.getRoleById(number));
//        }
        //user.setPassword();
        if (number == 1 || number == 2 || number == 3) {
            user.setRole(Collections.singletonList(roleService.getRoleById(number)));
        }
        return userRepository.save(user);
    }

    //It is get method(head)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //It is get method(head)
    public User getUserById(Long userId){
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public User modifyUser(User user){
        return userRepository.save(user);
    }

    //it is delete method(head)
    public User deleteUser(Long userId){
        User user = getUserById(userId);
        userRepository.deleteById(userId);
        return  user;
    }


    public Optional<User> findUserByUserName(String userName) {


        List<User> userList = userRepository.findAll();


        if (userList.isEmpty()) {


            User user1 = createDefaultUser.createDefaultUser();

            userRepository.save(user1);

        }

        Optional<User> opt = userRepository.findUserByUserName(userName);


        return opt;

    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        Optional<User> userOpt = findUserByUserName(username);

        User user = userOpt.get();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),user.getRole());
        return userDetails;
    }



}
