package com.revature.bibimbop.customer;

import com.revature.bibimbop.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.bibimbop.util.interfaces.Crudable;
import com.revature.bibimbop.util.ConnectionFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.io.IOException;


public class CustomerDao {

    //    // MVP - Add items to the menu
    public CustomerModel createCustomer(CustomerModel newCustomer) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newCustomer);
            transaction.commit();
            return newCustomer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }


    public CustomerModel updateCustomer(String customerUsername, String fName, String lName, String password, BigDecimal balance, Integer isAdmin) {
        try {
            CustomerModel updatedCustomer = new CustomerModel(customerUsername, fName, lName, password, balance, isAdmin);
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedCustomer);
            transaction.commit();

        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
        return followUpUpdateCustomer(customerUsername);
    }

    public CustomerModel followUpUpdateCustomer(String customerUsername) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            CustomerModel foundCustomer = session.get(CustomerModel.class, customerUsername);
            transaction.commit();
            return foundCustomer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }


    public boolean deleteByCustomerUsername(String customerUsername) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            CustomerModel deletedCustomer = session.load(CustomerModel.class, customerUsername);
            session.remove(deletedCustomer);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }


}

