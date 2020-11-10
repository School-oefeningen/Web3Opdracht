package ui.controller;

import domain.model.Person;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dashboard extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Person p = Checker.getUserInSession(request);
        System.out.println(p.getRegister());

        return "dashboard.jsp";
    }
}
