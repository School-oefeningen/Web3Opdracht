package ui.controller;

import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ContactsOverview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("user");
        List<Contact> contacts = null;

        if (person.getUserid().equals("admin")) contacts = contactService.getAll();
        else contacts = contactService.getAllFromUser(person.getUserid());

        request.setAttribute("contacts", contacts);
        return "contactsOverview.jsp";
    }
}