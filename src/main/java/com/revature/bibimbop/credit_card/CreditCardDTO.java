package com.revature.bibimbop.credit_card;

public class CreditCardDTO {

    private String ccNumber;
    private String ccName;
    private Integer cvv;
    private String expDate;
    private Integer zip;
    private Double limits;
    private String customerUsername;
    private String tableSelection;
    private String newCellName;
    private String oldCellName;

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

    public String getTableSelection() {
        return tableSelection;
    }

    public void setTableSelection(String tableSelection) {
        this.tableSelection = tableSelection;
    }

    public String getNewCellName() {
        return newCellName;
    }

    public void setNewCellName(String newCellName) {
        this.newCellName = newCellName;
    }

    public String getOldCellName() {
        return oldCellName;
    }

    public void setOldCellName(String oldCellName) {
        this.oldCellName = oldCellName;
    }
}