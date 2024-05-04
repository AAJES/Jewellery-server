package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankDetailsId;
    private String IFSCCode;
    private String accountNo;
    private String accountHolderName;
    private String branch;
    private String branchAddress;
    private String qrCode;
    private Boolean active;
    private String description;

    @CreationTimestamp
    private Date trDate;

    public BankDetails() {
    }

    public BankDetails(Integer bankDetailsId, String IFSCCode, String accountNo, String accountHolderName, String branch, String branchAddress, String qrCode, Boolean active, String description, Date trDate) {
        this.bankDetailsId = bankDetailsId;
        this.IFSCCode = IFSCCode;
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.branch = branch;
        this.branchAddress = branchAddress;
        this.qrCode = qrCode;
        this.active = active;
        this.description = description;
        this.trDate = trDate;
    }

    public Integer getBankDetailsId() {
        return bankDetailsId;
    }

    public void setBankDetailsId(Integer bankDetailsId) {
        this.bankDetailsId = bankDetailsId;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    @Override
    public String toString() {
        return "BankDetails{" +
                "bankDetailsId=" + bankDetailsId +
                ", IFSCCode='" + IFSCCode + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", branch='" + branch + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", active=" + active +
                ", description='" + description + '\'' +
                ", trDate=" + trDate +
                '}';
    }
}
