package com.revature.bibimbop.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bibimbop.util.exceptions.InvalidRequestException;
import com.revature.bibimbop.util.exceptions.ResourcePersistenceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.bibimbop.util.interfaces.Headable.addHeads;


public class MenuServlet extends HttpServlet {

    private final MenuDao mDao;
    private final MenuServices mServ;
    private final ObjectMapper mapper;

    public MenuServlet(MenuDao mDao, MenuServices mServ,ObjectMapper mapper) {
        this.mDao = mDao;
        this.mServ = mServ;
        this.mapper = mapper;
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        addHeads(req, resp);
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
//        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }


    //CREATE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doOptions(req, resp);
        addHeads(req, resp);

        MenuModel addedItem;
        try {
            MenuModel newMenuItem = mapper.readValue(req.getInputStream(), MenuModel.class);
            addedItem = mServ.create(newMenuItem);
        } catch (InvalidRequestException e) {
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }

        String payload = mapper.writeValueAsString(addedItem);

        resp.getWriter().write("Added the new menu item, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    //UPDATE
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);
        MenuDTO pass = mapper.readValue(req.getInputStream(), MenuDTO.class);

        MenuModel theResults = mDao.updateMenu(pass.getMenuItem(),pass.getCost(), pass.getProtein(), pass.getIsSubstitutable());


        String payload = mapper.writeValueAsString(theResults);

        resp.getWriter().write("Updated the menu item, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    //DELETE
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);
        MenuDTO pass = mapper.readValue(req.getInputStream(), MenuDTO.class);

        try {
            boolean deletedMenuItem = mDao.deleteByMenuItem(pass.getMenuItem());

            String payload = mapper.writeValueAsString(deletedMenuItem);

            resp.getWriter().write("Delete menu item from the database, see true below ");
            req.getSession().invalidate();
            resp.getWriter().write(payload);
            resp.setStatus(201);
        } catch (ResourcePersistenceException e) {
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e) {
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }

    //READ
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addHeads(req, resp);
        ArrayList<MenuModel> gotTheMenu = mDao.findAllMenuItems();

        String payload = mapper.writeValueAsString(gotTheMenu);

        resp.getWriter().write("Menu items populated, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

}
