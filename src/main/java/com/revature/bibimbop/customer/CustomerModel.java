package com.revature.bibimbop.customer;

import javax.persistence.*;


public class CustomerModel {

    private String customerUsername;
    private String fName;
    private String lName;
    private String password;
    private double balance;
    private Integer isAdmin;

    public CustomerModel(String customerUsername, String fName, String lName, String password, double balance, Integer isAdmin) {
        this.customerUsername = customerUsername;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public CustomerModel() {

    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "customerUsername='" + customerUsername + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", isAdmin=" + isAdmin +
                '}';
    }


}