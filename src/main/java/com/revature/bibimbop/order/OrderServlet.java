package com.revature.bibimbop.order;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends HttpServlet {

    private final OrderDao oDao;
    private final ObjectMapper mapper;

    public OrderServlet(OrderDao oDao, ObjectMapper mapper) {
        this.oDao = oDao;
        this.mapper = mapper;
    }



    //CREATE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        OrderDTO pass = mapper.readValue(req.getInputStream(), OrderDTO.class);

        OrderModel addedOrder = oDao.createCustomOrder(pass.getId(), pass.getMenuItem(), pass.getComment(), pass.getIsFavorite(), pass.getOrderDate(), pass.getCustomerUsername());

        OrderModel theOrder = oDao.followUpCreateCustomOrder(pass.getId());

        String payload = mapper.writeValueAsString(theOrder);

        resp.getWriter().write("Added the order, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //READ
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        OrderDTO pass = mapper.readValue(req.getInputStream(), OrderDTO.class);

        OrderModel[] orders = oDao.viewAllByDate(pass.getTheDate());

        String payload = mapper.writeValueAsString(orders);

        resp.getWriter().write("Orders populated, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

}