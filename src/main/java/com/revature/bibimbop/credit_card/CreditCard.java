package com.revature.bibimbop.credit_card;

import javax.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCardData {

    @Id


    @Column(name = "cc_number")

    private String cardNumber;
    @Column(name = "cc_name")
    private String cardName;

    private int cvv;

    @Column(name = "zip")
    private  int zipCode;

    @Column(name = " customer_username")
    private String username;



    public CreditCardData(String cardNumber, String cardName, int cvv, String expDate, int zipCode, int creditLimit, String username) {
        super();
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.zipCode = zipCode;
        this.username = username;
    }


    public CreditCardData() {

    }

    // Getters & Setters
    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "CreditCardData{" +
                "cardnumber='" + cardNumber + '\'' +
                ", cardname='" + cardName + '\'' +
                ", zipcode='" + zipCode + '\'' +
                ", creditlimit='" + creditLimit + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
