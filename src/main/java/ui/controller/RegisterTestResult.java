package ui.controller;

import domain.model.Person;
import domain.model.TestResult;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterTestResult extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Person person = Checker.getUserInSession(request);

        TestResult testResult = contactTracingService.getTestResultFromUser(person.getUserid());
        if (testResult != null) request.setAttribute("testResult", testResult);

        return "registerTestResult.jsp";
    }
}