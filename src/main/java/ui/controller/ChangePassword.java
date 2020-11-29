package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("yes") == null) return "Controller?command=Home";

        String newPassword = request.getParameter("newPassword");
        Person person = (Person) request.getSession().getAttribute("user");

        person.setPasswordHashed(newPassword);

        contactTracingService.updatePerson(person);

        request.getSession().invalidate();
        request.setAttribute("success", "Your password has been changed! Please log in again.");
        return "Controller?command=Home";
    }
}