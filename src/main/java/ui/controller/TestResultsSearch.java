package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.TestResult;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestResultsSearch extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Person person = Checker.getUserInSession(request);

        TestResult testResult = contactTracingService.getTestResultFromUser(person.getUserid());

        if (testResult == null) {
            request.setAttribute("error", "You are not positive to Covid-19!");
            return "search.jsp";
        }

        List<Contact> contacts = contactTracingService.getAllContactsFromUserAfterDate(testResult);

        if (contacts.isEmpty()) request.setAttribute("error", "No contacts to show.");

        request.setAttribute("testResult", testResult);
        request.setAttribute("contacts", contacts);
        return "search.jsp";
    }
}