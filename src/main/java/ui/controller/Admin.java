package ui.controller;

import domain.model.NotAuthorizedException;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Checker.roleIsAdmin(request);
        return "admin.jsp";
    }
}