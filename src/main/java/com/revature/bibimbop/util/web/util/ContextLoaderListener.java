package com.revature.bibimbop.util.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bibimbop.order.OrderDao;
import com.revature.bibimbop.menu.MenuDao;
import com.revature.bibimbop.menu.MenuServlet;
import com.revature.bibimbop.credit_card.CreditCardDao;
import com.revature.bibimbop.credit_card.CreditCardServlet;
import com.revature.bibimbop.customer.CustomerDao;
import com.revature.bibimbop.customer.CustomerServlet;

import com.revature.bibimbop.order.OrderServices;
import com.revature.bibimbop.credit_card.ElementTypeServices;
import com.revature.bibimbop.menu.MenuServices;
import com.revature.bibimbop.customer.CustomerServices;

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

        // Instantiate all Daos first
        CustomerDao customerDao = new CustomerDao();
        CreditCardDao creditCardDao = new CreditCardDao();
        MenuDao menuDao = new MenuDao();
        OrderDao orderDao = new OrderDao();

        // Instantiate and initialize the services with their dao dependency
        CustomerServices customerServices = new CustomerServices(customerDao);
        MenuServices menuServices = new MenuServices(menuDao);
        OrderServices orderServices = new OrderServices(orderDao, orderDao);
        CreditCardServices creditCardServices = new CreditCardServices(orderDao,creditcardDao, menuDao);


        AuthServlet authServlet = new AuthServlet(customerServices, mapper);
        CustomerServlet customerServlet = new CustomerServlet(customerServices, mapper);
        MenuServlet menuServlet = new menuServlet(menuServices, mapper);
        OrderServlet orderServlet = new OrderServlet(orderServices, mapper);
        CreditCardServlet creditCardServlet = new CreditCardServlet(creditCardDao, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("CustomerServlet", customerServlet).addMapping("/customer/*");
        context.addServlet("MenuServlet", menuServlet).addMapping("/menu/*");
        context.addServlet("OrderServlet", orderServlet).addMapping("/order/*");
        context.addServlet("CreditCardServlet", creditCardServlet).addMapping("/creditCard/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}