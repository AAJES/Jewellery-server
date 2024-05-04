package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModeOfPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modeOfPaymentId;
    private String modeOfPayment;
}
