package com.revature.bibimbop.util.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bibimbop.order.OrderDao;
import com.revature.bibimbop.menu.MenuDao;
import com.revature.bibimbop.menu.MenuServlet;
import com.revature.bibimbop.credit_card.CreditCardDao;
import com.revature.bibimbop.credit_card.CreditCardServlet;
import com.revature.bibimbop.customer.CustomerDao;
import com.revature.bibimbop.customer.CustomerServlet;

import com.revature.bibimbop.order.OrderDTO;
import com.revature.bibimbop.credit_card.CreditCardDTO;
import com.revature.bibimbop.menu.MenuDTO;
import com.revature.bibimbop.customer.CustomerDTO;

import com.revature.bibimbop.order.OrderServlet;
import com.revature.bibimbop.util.web.servlets.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Dao first
        CustomerDao customerDao = new CustomerDao();
        CreditCardDao creditCardDao = new CreditCardDao();
        MenuDao menuDao = new MenuDao();
        OrderDao orderDao = new OrderDao();


        CustomerServlet customerServlet = new CustomerServlet(customerDao, mapper);
        MenuServlet menuServlet = new MenuServlet(menuDao, mapper);
        OrderServlet orderServlet = new OrderServlet(orderDao, mapper);
        CreditCardServlet creditCardServlet = new CreditCardServlet(creditCardDao, mapper);

        ServletContext context = sce.getServletContext();

        context.addServlet("MenuServlet", menuServlet).addMapping("/menu/*");
        context.addServlet("CustomerServlet", customerServlet).addMapping("/customer/*");
        context.addServlet("OrderServlet", orderServlet).addMapping("/order/*");
        context.addServlet("CreditCardServlet", creditCardServlet).addMapping("/creditCard/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}