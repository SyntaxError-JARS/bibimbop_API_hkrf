package com.revature.bibimbop.credit_card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bibimbop.util.exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.revature.bibimbop.util.interfaces.Headable.addHeads;

public class CreditCardServlet extends HttpServlet {

    private final CreditCardDao cDao;
    private final CreditCardServices ccServ;
    private final ObjectMapper mapper;

    public CreditCardServlet(CreditCardDao cDao, CreditCardServices ccServ, ObjectMapper mapper) {
        this.cDao = cDao;
        this.ccServ = ccServ;
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
        CreditCardModel addedCreditCard;
        try {
            CreditCardModel newCreditCard = mapper.readValue(req.getInputStream(), CreditCardModel.class);
            addedCreditCard = ccServ.create(newCreditCard);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }

        String payload = mapper.writeValueAsString(addedCreditCard);

        resp.getWriter().write("Added the new credit card, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //UPDATE
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);
        CreditCardDTO pass = mapper.readValue(req.getInputStream(), CreditCardDTO.class);

        CreditCardModel firstResult = cDao.updateCreditCard(pass.getCcNumber(), pass.getCcName(), pass.getCvv(), pass.getExpDate(), pass.getZip(), pass.getLimits(), pass.getCustomerUsername());

        String payload = mapper.writeValueAsString(firstResult);

        resp.getWriter().write("Updated the credit card, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //DELETE
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);
        CreditCardDTO pass = mapper.readValue(req.getInputStream(), CreditCardDTO.class);

        boolean deleteTrue = cDao.deleteCreditCardByCCNumber(pass.getCcNumber());

        String payload = mapper.writeValueAsString(deleteTrue);

        resp.getWriter().write("Account was deleted, see true near bottom to verify \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

}