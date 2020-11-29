package ui.controller;

import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddContactForm extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Checker.roleIsNotAdmin(request);

        request.setAttribute("datePrevious", LocalDate.now());
        request.setAttribute("hourPrevious", LocalTime.now().withSecond(0).withNano(0));
        return "addContactForm.jsp";
    }
}