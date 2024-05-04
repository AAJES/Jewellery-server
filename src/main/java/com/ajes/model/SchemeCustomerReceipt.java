package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class SchemeCustomerReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schemeCustomerRecId;

    private  Integer customerLuckyNo;
    private String customerName;
    private String customerPhone;
    private String remark;

    @OneToOne
    private Scheme scheme;
    public SchemeCustomerReceipt() {
        super();
    }

    public SchemeCustomerReceipt(Integer schemeCustomerRecId, Integer customerLuckyNo,
                                 String customerName, String customerPhone, String remark,Scheme scheme) {
        this.schemeCustomerRecId = schemeCustomerRecId;
        this.customerLuckyNo = customerLuckyNo;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.remark = remark;
        this.scheme=scheme;
    }

    public Integer getSchemeCustomerRecId() {
        return schemeCustomerRecId;
    }

    public void setSchemeCustomerRecId(Integer schemeCustomerRecId) {
        this.schemeCustomerRecId = schemeCustomerRecId;
    }

    public Integer getCustomerLuckyNo() {
        return customerLuckyNo;
    }

    public void setCustomerLuckyNo(Integer customerLuckyNo) {
        this.customerLuckyNo = customerLuckyNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString() {
        return "SchemeCustomerReceipt{" +
                "schemeCustomerRecId=" + schemeCustomerRecId +
                ", customerLuckyNo=" + customerLuckyNo +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", remark='" + remark + '\'' +
                ", scheme=" + scheme +
                '}';
    }
}
