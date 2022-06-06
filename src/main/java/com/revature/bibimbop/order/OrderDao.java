package com.revature.bibimbop.order;

import com.revature.bibimbop.util.ConnectionFactory;
import com.revature.bibimbop.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private EntityManager em;

    // MVP - combo - make order, add comment for is substitutable, and favorite order
    public OrderModel createCustomOrder(OrderModel newOrder){
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newOrder);
            transaction.commit();
            return newOrder;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        }finally {
            HibernateUtil.closeSession();
        }
    }

    // MVP - View past orders by date
    public ArrayList<OrderModel> viewAllByDate(){
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            ArrayList<OrderModel> ordersFoundByDate = (ArrayList<OrderModel>) session.createQuery("FROM OrderModel").list();
            transaction.commit();
            return ordersFoundByDate;
        }catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        }finally {
            HibernateUtil.closeSession();
        }
    }



}
