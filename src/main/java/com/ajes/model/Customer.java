package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
        private String customerName;
    private String phone1;
    private String phone2;
    private String location;
    private String email;

    @OneToOne
    private User user;

    @OneToOne
    private Gender gender;


//    @OneToMany
//    private List<Login> logins;
}
