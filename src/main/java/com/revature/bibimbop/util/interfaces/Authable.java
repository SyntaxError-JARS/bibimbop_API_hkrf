package com.revature.bibimbop.util.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface Authable {

    static boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authCustomer") == null){
            resp.getWriter().write("Unauthorized request - not log in as registered Customer");
            resp.setStatus(401); // Unauthorized
            return false;
        }
        return true;
    }
}
© 2022 GitHub, Inc.

