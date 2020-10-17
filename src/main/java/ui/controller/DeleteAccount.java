package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAccount extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("yes") == null) {
            return "Controller?command=Home";
        }

        Person p = (Person) request.getSession().getAttribute("user");
        service.delete(p.getUserid());
        request.setAttribute("succes", "Your account has succesfully been deleted.");
        return "Controller?command=Logout";
    }
}