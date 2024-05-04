package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginId;
    private String systemDetails;

    @CreationTimestamp
    private Date loginDate;
//    @ManyToOne
//    private List<User> users;
    @OneToOne
    private User user;
}



