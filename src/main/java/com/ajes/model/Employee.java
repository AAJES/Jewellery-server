package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String employeeName;
    private Integer age;
    private String phoneNumber;
    private String Address;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date trdate;

    @OneToOne
    private Gender gender;

    @OneToOne
    private User user;
}
