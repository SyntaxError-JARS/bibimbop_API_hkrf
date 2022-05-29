package com.revature.bibimbop.credit_card;


import com.revature.bibimbop.menu.MenuDao;
//import com.revature.bibimbop.credit_card.CreditCardDao;
import com.revature.bibimbop.credit_card.CreditCard;
import com.revature.bibimbop.util.exceptions.InvalidRequestException;
import com.revature.bibimbop.util.exceptions.ResourcePersistenceException;

import java.io.IOException;
import java.util.ArrayList;

//public class CreditCardServices {
//
//    private CreditCardDao creditCardDao;
//
//    public CreditCardServices(CreditCardDao creditCardDao){
//        this.creditCardDao = creditCardDao;
//    }
//
//    public CreditCard CreateCreditCard(CreditCard newCreditCard){
//
//        if(!validateCreditCard (newCreditCard)) {
//            throw new InvalidRequestException("Credit card input was not validated, either empty Strings or null values");
//        }
//
//        CreditCard persistedCreditCard = creditCardDao.Create(newCreditCard);
//        if(persistedCreditCard == null) {
//            throw new ResourcePersistenceException("Credit card was not persisted to the database");
//        }
//        return newCreditCard;
//    }
//
//
//    public ArrayList<CreditCard> readAll() throws IOException {
//        ArrayList<CreditCard> creditCard = (ArrayList<CreditCard>) creditCardDao.findAll();
//        return creditCard;
//    }
//    public CreditCard readByID(String username){
//        CreditCard creditCard = creditCardDao.findByID(username);
//        return creditCard;
//    }
//
//    public CreditCard update(CreditCard updatedCreditCard){
//        if(!creditCardDao.update((updatedCreditCard))){
//            return null;
//        }
//        return updatedCreditCard;
//    }
//
//    public boolean delete(String creditCard){
//        return creditCardDao.delete(creditCard);
//    }
//
//    public boolean validateCreditCard(CreditCard newCreditCard){
//        if(newCreditCard == null) return false;
//        if(newCreditCard.getCardNumber() == null || newCreditCard.getCardNumber().trim().equals((""))) return false;
//        if(newCreditCard.getCardName() == null || newCreditCard.getCardName().trim().equals((""))) return false;
//        if(newCreditCard.getCvv() == 0 ) return false;
//        if(newCreditCard.getExpDate() == null || newCreditCard.getExpDate().trim().equals((""))) return false;
//        if(newCreditCard.getZipCode() == 0 ) return false;
//        if(newCreditCard.getCreditLimit() == 0 ) return false;
//        if(newCreditCard.getUsername() == null || newCreditCard.getUsername().trim().equals((""))) return false;
//        return true;
//    }
//}
