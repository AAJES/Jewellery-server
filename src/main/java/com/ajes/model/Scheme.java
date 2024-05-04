package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schemeId;
    private String schemeName;
    private Integer schemeAmount;

    private Integer totalSizeCustomer;

    @CreationTimestamp
    private Date trDate;

    private Date startDate;
    private Date endDate;
    private Integer amountPerMonth;

    public Scheme() {
    }

    public Scheme(Integer schemeId, String schemeName, Integer schemeAmount,
                  Integer totalSizeCustomer, Date trDate, Date startDate, Date endDate, Integer amountPerMonth) {
        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.schemeAmount = schemeAmount;
        this.totalSizeCustomer = totalSizeCustomer;
        this.trDate = trDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountPerMonth = amountPerMonth;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Integer getSchemeAmount() {
        return schemeAmount;
    }

    public void setSchemeAmount(Integer schemeAmount) {
        this.schemeAmount = schemeAmount;
    }

    public Integer getTotalSizeCustomer() {
        return totalSizeCustomer;
    }

    public void setTotalSizeCustomer(Integer totalSizeCustomer) {
        this.totalSizeCustomer = totalSizeCustomer;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAmountPerMonth() {
        return amountPerMonth;
    }

    public void setAmountPerMonth(Integer amountPerMonth) {
        this.amountPerMonth = amountPerMonth;
    }

    @Override
    public String toString() {
        return "Scheme{" +
                "schemeId=" + schemeId +
                ", schemeName='" + schemeName + '\'' +
                ", schemeAmount=" + schemeAmount +
                ", totalSizeCustomer=" + totalSizeCustomer +
                ", trDate=" + trDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amountPerMonth=" + amountPerMonth +
                '}';
    }
}
