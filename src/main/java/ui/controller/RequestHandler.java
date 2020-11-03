package ui.controller;

import domain.service.ContactService;
import domain.service.PersonService;
import domain.service.TestResultService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected PersonService service;
    protected ContactService contactService;
    protected TestResultService testResultService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    public void setModel(PersonService service, ContactService contactService, TestResultService testResultService) {
        this.service = service;
        this.contactService = contactService;
        this.testResultService = testResultService;
    }

    public PersonService getService() {
        return service;
    }
}