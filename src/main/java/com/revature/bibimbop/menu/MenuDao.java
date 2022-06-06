package com.revature.bibimbop.menu;

import com.revature.bibimbop.credit_card.CreditCardModel;
import com.revature.bibimbop.order.OrderModel;
import com.revature.bibimbop.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao {

    //    // MVP - Add items to the menu
    public MenuModel createMenu(MenuModel newMenuItem) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newMenuItem);
            transaction.commit();
            return newMenuItem;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }



    //    // MVP - View all items on the menu without needing to Register or Login
    public ArrayList<MenuModel> findAllMenuItems(){
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            ArrayList<MenuModel> allMenuItemsList = (ArrayList<MenuModel>) session.createQuery("FROM MenuModel").list();
            transaction.commit();
            return allMenuItemsList;
        }catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    //     MVP - Delete items to the menu
    public boolean deleteByMenuItem(String menuItem){
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            MenuModel deletedMenuItem = session.load(MenuModel.class, menuItem);
            session.remove(deletedMenuItem);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return false;
        }finally {
            HibernateUtil.closeSession();
        }
    }

    //    // MVP - Update items to the menu
    public MenuModel updateMenu(String menuItem, BigDecimal cost, String protein, Integer isSubstitutable){
        try {
            MenuModel updatedMenuItem = new MenuModel(menuItem, cost, protein, isSubstitutable);
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedMenuItem);
            transaction.commit();

        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
        return followUpUpdateMenu(menuItem);
    }

    public MenuModel followUpUpdateMenu(String menuItem){
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            MenuModel foundMenuItem = session.get(MenuModel.class, menuItem);
            transaction.commit();
            return foundMenuItem;
        }catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }


}