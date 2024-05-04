package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerId;
    private String ownerName;
    private String phone;
    private String password;

    @OneToOne
    private User user;

//    @OneToOne
//    private Login login;


    public Owner() {
    }

    public Owner(Integer ownerId, String ownerName, String phone, String password, User user) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.phone = phone;
        this.password = password;
        this.user = user;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
