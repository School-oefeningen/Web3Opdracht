package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.TestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestResultsSearch extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("user");
        TestResult testResult = testResultService.getTestResultFromUser(person.getUserid());

        if (testResult == null) {
            request.setAttribute("error", "You are not positive to Covid-19!");
            return "search.jsp";
        }

        List<Contact> contacts = contactService.getAllFromUserAfterDate(testResult);

        if (contacts.isEmpty()) request.setAttribute("error", "No contacts to show.");

        request.setAttribute("testResult", testResult);
        request.setAttribute("contacts", contacts);
        return "search.jsp";
    }
}