package ui.controller;

import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Checker.logout(request);
        request.setAttribute("success", "You have been logged out successfully.");
        return "Controller?command=Home";
    }
}
