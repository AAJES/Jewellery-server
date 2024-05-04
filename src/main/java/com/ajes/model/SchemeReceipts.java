package com.ajes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class SchemeReceipts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schemeReceiptId;

    @OneToOne
    private Scheme scheme;

    @OneToOne
    private SchemeCustomerReceipt schemeCustomerReceipt;


    private Date paymentDate;

    private String schemePaymentDate;

    private Integer amount;

    @OneToOne
    private ModeOfPayment modeOfPayment;


    public SchemeReceipts() {
    }

    public SchemeReceipts(Integer schemeReceiptId, Scheme scheme, SchemeCustomerReceipt schemeCustomerReceipt,
                          Date paymentDate, String schemePaymentDate, Integer amount, ModeOfPayment modeOfPayment) {
        this.schemeReceiptId = schemeReceiptId;
        this.scheme = scheme;
        this.schemeCustomerReceipt = schemeCustomerReceipt;
        this.paymentDate = paymentDate;
        this.schemePaymentDate = schemePaymentDate;
        this.amount = amount;
        this.modeOfPayment = modeOfPayment;
    }

    public Integer getSchemeReceiptId() {
        return schemeReceiptId;
    }

    public void setSchemeReceiptId(Integer schemeReceiptId) {
        this.schemeReceiptId = schemeReceiptId;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public SchemeCustomerReceipt getSchemeCustomerReceipt() {
        return schemeCustomerReceipt;
    }

    public void setSchemeCustomerReceipt(SchemeCustomerReceipt schemeCustomerReceipt) {
        this.schemeCustomerReceipt = schemeCustomerReceipt;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSchemePaymentDate() {
        return schemePaymentDate;
    }

    public void setSchemePaymentDate(String schemePaymentDate) {
        this.schemePaymentDate = schemePaymentDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    @Override
    public String toString() {
        return "SchemeReceipts{" +
                "schemeReceiptId=" + schemeReceiptId +
                ", scheme=" + scheme +
                ", schemeCustomerReceipt=" + schemeCustomerReceipt +
                ", paymentDate=" + paymentDate +
                ", schemePaymentDate='" + schemePaymentDate + '\'' +
                ", amount=" + amount +
                ", modeOfPayment=" + modeOfPayment +
                '}';
    }
}
