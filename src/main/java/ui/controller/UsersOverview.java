package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersOverview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> people = contactTracingService.getPersonService().getAll();
        request.setAttribute("people", people);
        return "usersOverview.jsp";
    }
}