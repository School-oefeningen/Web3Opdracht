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

        service.delete(request.getParameter("userId"));
        request.setAttribute("succes", "Your account has succesfully been deleted.");

        Person person = (Person) request.getSession().getAttribute("user");
        if (person.getUserid().equals("admin")) return "Controller?command=UsersOverview";
        else return "Controller?command=Logout";
    }
}