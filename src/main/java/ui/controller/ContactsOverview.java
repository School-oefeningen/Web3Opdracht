package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.Role;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ContactsOverview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Person person = Checker.getUserInSession(request);

        List<Contact> contacts;

        if (person.getRole() == Role.ADMIN) contacts = contactService.getAll();
        else contacts = contactService.getAllFromUser(person.getUserid());

        request.setAttribute("contacts", contacts);
        return "contactsOverview.jsp";
    }
}