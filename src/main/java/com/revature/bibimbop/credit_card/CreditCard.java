package com.revature.bibimbop.credit_card;

import javax.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id

    @Column(name = "cc_number")

    private String cardNumber;
    @Column(name = "cc_name")
    private String cardName;

    private int cvv;

    @Column(name = " customer_username")
    private String username;



    public CreditCard(String cardNumber, String cardName,  String username) {
        super();
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.username = username;
    }


    public CreditCard() {

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
                ", username='" + username + '\'' +
                '}';
    }


}
