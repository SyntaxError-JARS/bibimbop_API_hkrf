package com.revature.bibimbop.credit_card;

import javax.persistence.*;
public class CreditCardModel {
    private String ccNumber;
    private String ccName;
    private Integer cvv;
    private String expDate;
    private Integer zip;
    private Double limits;
    private String customerUsername;

    public CreditCardModel(String ccNumber, String ccName, Integer cvv, String expDate, Integer zip, Double limits, String customerUsername) {
        this.ccNumber = ccNumber;
        this.ccName = ccName;
        this.cvv = cvv;
        this.expDate = expDate;
        this.zip = zip;
        this.limits = limits;
        this.customerUsername = customerUsername;
    }

    public CreditCardModel() {

    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Double getLimits() {
        return limits;
    }

    public void setLimits(Double limits) {
        this.limits = limits;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }
}