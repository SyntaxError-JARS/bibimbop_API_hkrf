package com.revature.bibimbop.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    private String fname;
    private String lname;
    @Id
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Int balance;



    public Customer(String fname, String lname, String username, String password, Int balance) {
        super();
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Customer(String password){
        this.password = password;
    }

    public Customer() {

    }

    // Getters & Setters
    public String getFname() {
        return fname;
    }


    public void setFname(String fname) {
        this.fname = fname;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Int getBalance() {
        return balance;
    }

    public void setBalance(Int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }


}