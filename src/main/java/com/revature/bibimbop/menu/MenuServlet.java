//package com.revature.bibimbop.menu;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//public class MenuServlet extends HttpServlet {
//
//    private final MenuServices menuServices;
//    private final ObjectMapper mapper;
//
//    public MenuServlet(MenuServices menuServices, ObjectMapper mapper) {
//        this.menuServices = menuServices;
//        this.mapper = mapper;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(req.getParameter("menuName") != null){
//            Menu menu = menuServices.readById(req.getParameter("menuName"));
//            String payload = mapper.writeValueAsString(menu);
//            resp.getWriter().write(payload);
//            return;
//        }
//
//        List<Menu> abilities = menuServices.readAll();
//        String payload = mapper.writeValueAsString(abilities);
//
//        resp.getWriter().write(payload);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Menu menus = mapper.readValue(req.getInputStream(), Menu.class);
//        Menu menu = menuServices.create(menus);
//
//        String payload = mapper.writeValueAsString(menu);
//
//        resp.getWriter().write(payload);
//        resp.setStatus(201);
//
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//
//    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        HttpSession httpSession = req.getSession();
//        if(httpSession.getAttribute("authMenu") == null){
//            resp.getWriter().write("Unauthorized request - not log in as registered customer");
//            resp.setStatus(401); // Unauthorized
//            return false;
//        }
//        return true;
//    }
//}
