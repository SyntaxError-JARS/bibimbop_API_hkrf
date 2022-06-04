package com.revature.bibimbop.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bibimbop.util.exceptions.InvalidRequestException;

import com.revature.bibimbop.menu.MenuDTO;
import com.revature.bibimbop.menu.MenuModel;
import com.revature.bibimbop.order.OrderModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.revature.bibimbop.util.interfaces.Headable.addHeads;

public class CustomerServlet extends HttpServlet {

    private final CustomerDao cDao;
    private final CustomerServices cServ;
    private final ObjectMapper mapper;

    public CustomerServlet(CustomerDao cDao, CustomerServices cServ, CustomerServices cServ1, ObjectMapper mapper) {
        this.cDao = cDao;
        this.cServ = cServ1;
        this.mapper = mapper;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
    //CREATE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);

        CustomerModel addedCustomer;
        try {
            CustomerModel newCustomer = mapper.readValue(req.getInputStream(), CustomerModel.class);
            addedCustomer =cServ.create(newCustomer);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }

        String payload = mapper.writeValueAsString(addedCustomer);

        resp.getWriter().write("Added the new customer, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    //UPDATE
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);
        CustomerDTO pass = mapper.readValue(req.getInputStream(), CustomerDTO.class);

        CustomerModel theResults = cDao.updateCustomer(pass.getCustomerUsername(),pass.getfName(), pass.getlName(), pass.getPassword(), pass.getBalance(), pass.getIsAdmin());


        String payload = mapper.writeValueAsString(theResults);

        resp.getWriter().write("Updated customer, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //DELETE
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);
        CustomerDTO pass = mapper.readValue(req.getInputStream(), CustomerDTO.class);

        boolean deleteTrue = cDao.deleteByCustomerUsername(pass.getCustomerUsername());

        String payload = mapper.writeValueAsString(deleteTrue);

        resp.getWriter().write("Customer was deleted, see true near bottom to verify \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }



}
