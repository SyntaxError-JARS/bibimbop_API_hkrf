package com.revature.bibimbop.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bibimbop.util.exceptions.InvalidRequestException;
import com.revature.bibimbop.util.exceptions.ResourcePersistenceException;
import com.revature.bibimbop.util.interfaces.Authable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.bibimbop.util.interfaces.Authable.checkAuth;

// @WebServlet("/trainers")
public class CustomerServlet extends HttpServlet implements Authable {

    private final CustomerServices customerServices;
    private final ObjectMapper mapper;

    public CustomerServlet(CustomerServices customerServices, ObjectMapper mapper) {
        this.customerServices = customerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // The below code allows to split information from the endpoint after the /trainers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);

        // Handling the query params in the /trainers?id=x&email=y
        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and username " + req.getParameter("id") + " " + req.getParameter("username") );
            return;
        }

        // Handling the query params in the endpoint /trainers?id=x
        if(req.getParameter("id") != null){
            Customer customer;
            try {
                customer = customerServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistenceException e){
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(customer);
            resp.getWriter().write(payload);
            return;
        }

        List<Customer> customers = customerServices.readAll();
        String payload = mapper.writeValueAsString(customers);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req, resp)) return;
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

        Customer reqCustomer = mapper.readValue(req.getInputStream(), Customer.class);

        if(authCustomer.getUsername().equals(reqCustomer.getUsername())) {

            Customer updatedCustomer = customerServices.update(reqCustomer);

            String payload = mapper.writeValueAsString(updatedCustomer);
            resp.getWriter().write(payload);
        } else {
            resp.getWriter().write("Username provided does not match the user currently logged in");
            resp.setStatus(403);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Customer persistedCustomer;
        try {
            Customer customer = mapper.readValue(req.getInputStream(), Customer.class); // from JSON to Java Object (Pokemon)
            persistedCustomer = customerServices.create(customer);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }
        String payload = mapper.writeValueAsString(persistedCustomer); // Mapping from Java Object () to JSON

        resp.getWriter().write("Persisted the provided customer as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        if(!checkAuth(req,resp)) return;
        if(req.getParameter("username") == null){
            resp.getWriter().write("In order to delete, please provide your user email as a verification into the url with ?email=your@email.here");
            resp.setStatus(401);
            return;
        }

        String username = req.getParameter("username");
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

        if(!authCustomer.getUsername().equals(username)){
            resp.getWriter().write("Username provided does not match the user logged in, double check for confirmation of deletion");
            return;
        }

        try {
            customerServices.delete(username);
            resp.getWriter().write("Delete username from the database");
            req.getSession().invalidate();
        } catch (ResourcePersistenceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }
}