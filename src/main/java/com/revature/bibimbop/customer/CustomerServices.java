package com.revature.bibimbop.customer;

import com.revature.bibimbop.util.exceptions.AuthenticationException;
import com.revature.bibimbop.util.exceptions.InvalidRequestException;
import com.revature.bibimbop.util.exceptions.ResourcePersistenceException;
import com.revature.bibimbop.util.interfaces.Serviceable;
import java.io.IOException;
import java.util.List;

//public class CustomerServices implements Serviceable<Customer> {
//
//    private CustomerDao customerDao;

//    public CustomerServices(CustomerDao customerDao) {
//        this.customerDao = customerDao;
//    }
//
//    @Override
//    public List<Customer> readAll(){
//        // TODO: What trainerDao intellisense telling me?
//        List<Customer> customers = customerDao.findAll();
//        return customers;
//    }
//
//    @Override
//    public Customer readById(String id) throws ResourcePersistenceException{
//
//        Customer customer = customerDao.findById(id);
//        return customer;
//    }
//
//    @Override
//    public Customer update(Customer updatedCustomer) {
//        if (!customerDao.update(updatedCustomer)){
//            return null;
//        }
//
//        return updatedCustomer;
//    }
//
//    @Override
//    public boolean delete(String username) {
//        return customerDao.delete(username);
//    }
//
//    public boolean validateEmailNotUsed(String username){
//        return customerDao.checkUsername(username);
//    }
//
//    public Customer create(Customer newCustomer){
//        if(!validateInput(newCustomer)){ // checking if false
//            // TODO: throw - what's this keyword?
//            throw new InvalidRequestException("User input was not validated, either empty String or null values");
//        }
//
//        // TODO: Will implement with JDBC (connecting to our database)
//        if(validateEmailNotUsed(newCustomer.getUsername())){
//            throw new InvalidRequestException("Customer username is already in use. Please try again with another email or login into previous made account.");
//        }
//
//        Customer persistedCustomer = customerDao.create(newCustomer);
//
//        if(persistedCustomer == null){
//            throw new ResourcePersistenceException("Customer was not persisted to the database upon registration");
//        }
//        return persistedCustomer;
//    }
//
//    @Override
//    public boolean validateInput(Customer newCustomer) {
//        if(newCustomer == null) return false;
//        if(newCustomer.getFname() == null || newCustomer.getFname().trim().equals("")) return false;
//        if(newCustomer.getLname() == null || newCustomer.getLname().trim().equals("")) return false;
//        if(newCustomer.getUsername() == null || newCustomer.getUsername().trim().equals("")) return false;
//        if(newCustomer.getPassword() == null || newCustomer.getPassword().trim().equals("")) return false;
//        return newCustomer.getBalance() != null || !newCustomer.getBalance().trim().equals("");
//    }
//
//    public Customer authenticateCustomer(String username, String password){
//
//        if(password == null || password.trim().equals("") || username == null || username.trim().equals("")) {
//            throw new InvalidRequestException("Either email or password is an invalid entry. Please try logging in again");
//        }
//
//        Customer authenticatedCustomer = customerDao.authenticateCustomer(username, password);
//
//        if (authenticatedCustomer == null){
//            throw new AuthenticationException("Unauthenticated customer, information provided was not consistent with our database.");
//        }
//
//        return authenticatedCustomer;
//
//    }
//}