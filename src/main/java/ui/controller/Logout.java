package ui.controller;

import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Checker.isUserLoggedIn(request);
        Checker.logout(request);
        request.setAttribute("success", "You have been logged out successfully.");
        response.sendRedirect("Controller?command=Home");
        return "Controller?command=Home";
    }
}
