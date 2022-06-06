package com.revature.bibimbop.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bibimbop.menu.MenuModel;
import com.revature.bibimbop.util.exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.bibimbop.util.interfaces.Headable.addHeads;


public class OrderServlet extends HttpServlet {

    private final OrderDao oDao;
    private final OrderServices oServ;
    private final ObjectMapper mapper;

    public OrderServlet(OrderDao oDao, OrderServices oServ, ObjectMapper mapper) {
        this.oDao = oDao;
        this.oServ = oServ;
        this.mapper = mapper;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        addHeads(req, resp);
    }


    //CREATE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);

        OrderModel addedOrder;
        try {
            OrderModel newOrder = mapper.readValue(req.getInputStream(), OrderModel.class);
            addedOrder = oServ.create(newOrder);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }

        String payload = mapper.writeValueAsString(addedOrder);

        resp.getWriter().write("Added the new order, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //READ
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);

        ArrayList<OrderModel> gotDates = oDao.viewAllByDate();

        String payload = mapper.writeValueAsString(gotDates);

        resp.getWriter().write("Orders populated, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }



}