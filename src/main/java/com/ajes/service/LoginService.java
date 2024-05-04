package com.ajes.service;

import com.ajes.model.Login;
import com.ajes.model.User;
import com.ajes.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserService userService;

    //It is post method(body)
    public Login addLogin(Login login){
        //HttpServletRequest request = getRemoteAddr(); // Obtain the client's IP address
        return loginRepository.save(login);
    }

    //It is get method(head)
    public List<Login> getAll(){
        return loginRepository.findAll();
    }

    public Login getLoginByUser(Long userId){
        List<Long> list = new ArrayList<>();

        List<Login> list1 = getAll();

        User user = userService.getUserById(userId);

        for(Login l : list1){
            if(l.getUser().equals(user)){

                list.add(l.getLoginId());
            }
        }

       // Lo[] numbers = {10, 5, 20, 8, 15, 30, 25};

        // Initialize max to the first element in the array
//        Long max = list.get(0);
//
//
//        // Iterate through the array and update max if a higher value is found
//        for (Long number : list) {
//            System.out.println(number);
//            if (number > max) {
//                max = number;
//                return loginRepository.findById(max).get();
//            }
//        }
//        return null;

        if (list.isEmpty()) {
            throw new IllegalArgumentException("ArrayList is empty");
        }

        Long largest = list.get(0);

        for (Long number : list) {
            if (number > largest) {
                largest = number;
            }
        }

        return loginRepository.findById(largest).get();
    }


    //It is get method(head)
    public Login getLoginById(Long loginId){
        Optional<Login> optional = loginRepository.findById(loginId);
        if(optional.isPresent()){
            return optional.get();
        }
        else
            return  null;
    }

    //it is put method(head and body)
    public Login modifyLogin(Login login){
        return loginRepository.save(login);
    }

    //it is delete method(head)
    public Login deleteLogin(Long loginId){
        Login login = getLoginById(loginId);
        loginRepository.deleteById(loginId);
        return  login;
    }
}
