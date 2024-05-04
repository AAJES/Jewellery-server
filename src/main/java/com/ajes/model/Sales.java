package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesId;
    private Float totalAmount;
   // private Rate rateId;
    private Float discountAmount;
    private Boolean paymentStatus;
    private Float  wastage;
    private Float grossWeight;
    private Float netWeight;
    private Float actualAmount;
    private Integer quantity;
    private Boolean billGeneratedOrNo;
    private Integer amountPaid;
//    private  Long userId;
//    private Integer loginId;

    @CreationTimestamp
    private Date trdate;

    @OneToOne
    private Payment payment;

    @OneToOne
    private User user;

    @OneToOne
    private Bill bill;

    @OneToOne
    private Login login;
}
