package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class incentBilling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incentBillingId;
    private String customerName;
    private String customerPhoneNumber;




}
