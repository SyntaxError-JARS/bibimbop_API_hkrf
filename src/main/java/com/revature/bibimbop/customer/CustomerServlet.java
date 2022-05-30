package com.revature.bibimbop.customer;

import com.fasterxml.jackson.databind.ObjectMapper;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet {

    private final CustomerDao cDao;
    private final ObjectMapper mapper;

    public CustomerServlet(CustomerDao cDao, ObjectMapper mapper) {
        this.cDao = cDao;
        this.mapper = mapper;
    }


    //CREATE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        CustomerDTO pass = mapper.readValue(req.getInputStream(), CustomerDTO.class);

        CustomerModel firstResult = cDao.createCustomer(pass.getCustomerUsername(), pass.getfName(), pass.getlName(), pass.getPassword(), pass.getBalance(), pass.getIsAdmin());
        CustomerModel theObject = cDao.followUpCreateCustomer(pass.getCustomerUsername());

        String payload = mapper.writeValueAsString(theObject);

        resp.getWriter().write("Added customer, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //UPDATE
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        CustomerDTO pass = mapper.readValue(req.getInputStream(), CustomerDTO.class);

        CustomerModel firstResult = cDao.updateCustomer(pass.getCustomerUsername(), pass.getfName(), pass.getlName(), pass.getPassword(), pass.getBalance(), pass.getIsAdmin());
        CustomerModel theObject = cDao.followUPUpdateCustomer(pass.getCustomerUsername());

        String payload = mapper.writeValueAsString(theObject);

        resp.getWriter().write("Updated customer, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //DELETE
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        CustomerDTO pass = mapper.readValue(req.getInputStream(), CustomerDTO.class);

        boolean deleteTrue = cDao.deleteByCustomer(pass.getCustomerUsername());

        String payload = mapper.writeValueAsString(deleteTrue);

        resp.getWriter().write("Customer was deleted, see true near bottom to verify \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        CustomerModel[] customers = cDao.findAllCustomers();

        String payload = mapper.writeValueAsString(customers);

        resp.getWriter().write("Customers populated, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

}
