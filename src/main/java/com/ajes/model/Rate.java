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
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    @CreationTimestamp
    private Date trDate = new Date();
    private Boolean activeStatus;
    private Float ratePerGram;

    @OneToOne
    private Metal metal;

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", trDate=" + trDate +
                ", activeStatus=" + activeStatus +
                ", ratePerGram=" + ratePerGram +
                ", metal=" + metal +
                ", purity=" + purity +
                ", user=" + user +
                '}';
    }

    @OneToOne
    private Purity purity;

    @OneToOne
    private User user;

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Float getRatePerGram() {
        return ratePerGram;
    }

    public void setRatePerGram(Float ratePerGram) {
        this.ratePerGram = ratePerGram;
    }

    public Metal getMetal() {
        return metal;
    }

    public void setMetal(Metal metal) {
        this.metal = metal;
    }

    public Purity getPurity() {
        return purity;
    }

    public void setPurity(Purity purity) {
        this.purity = purity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
