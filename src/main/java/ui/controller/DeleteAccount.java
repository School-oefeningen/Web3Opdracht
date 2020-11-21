package ui.controller;

import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAccount extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("yes") == null) {
            return "Controller?command=Home";
        }

        String userId = request.getParameter("userId");
        contactTracingService.removePerson(userId);
        contactTracingService.removeContactsFromUser(userId);
        contactTracingService.removeTestResultFromUser(userId);
        request.setAttribute("succes", "The account and all its content has succesfully been deleted.");

        Person person = (Person) request.getSession().getAttribute("user");
        if (person.getRole() == Role.ADMIN) return "Controller?command=UsersOverview";
        else return "Controller?command=Logout";
    }
}