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
@Table(name="license")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer licenseId;
    private Date nextRenewalDate;
    private Float amountPaid;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date trDate;
    private String licenseType;
    private String code;
    private String yearMonth;
}
