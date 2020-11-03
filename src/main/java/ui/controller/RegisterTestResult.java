package ui.controller;

import domain.model.Person;
import domain.model.TestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterTestResult extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("user");
        TestResult testResult = testResultService.getTestResultFromUser(person.getUserid());

        if (testResult != null) request.setAttribute("testResult", testResult);
        return "registerTestResult.jsp";
    }
}