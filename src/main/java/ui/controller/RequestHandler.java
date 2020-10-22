package ui.controller;

import domain.service.ContactService;
import domain.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected PersonService service;
    protected ContactService contactService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    public void setModel(PersonService service, ContactService contactService) {
        this.service = service;
        this.contactService = contactService;
    }

    public PersonService getService() {
        return service;
    }
}
