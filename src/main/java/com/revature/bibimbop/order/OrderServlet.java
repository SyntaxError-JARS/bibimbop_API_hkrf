package com.revature.bibimbop.order;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private final OrderServices orderServices;
    private final ObjectMapper mapper;

    public OrderServlet(OrderServices orderServices, ObjectMapper mapper) {
        this.orderServices = orderServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            Order order = orderServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(order);
            resp.getWriter().write(payload);
            return;
        }

        List<Order> elementTypes = orderServices.readAll();
        String payload = mapper.writeValueAsString(elementTypes);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Order order = mapper.readValue(req.getInputStream(), Order.class);
        Order persistedOrder = orderServices.create(order);

        String payload = mapper.writeValueAsString(persistedOrder);
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authOrder") == null){
            resp.getWriter().write("Unauthorized request - not log in as registered customer");
            resp.setStatus(401); // Unauthorized
            return false;
        }
        return true;
    }

}
