package ui.controller;

import domain.model.Person;
import domain.model.TestResult;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class RegisterTestResult extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Checker.roleIsNotAdmin(request);
        Person person = Checker.getUserInSession(request);

        TestResult testResult = contactTracingService.getTestResultService().getTestResultFromUser(person.getUserid());
        if (testResult != null) request.setAttribute("testResult", testResult);

        request.setAttribute("currentDate", LocalDate.now());
        return "registerTestResult.jsp";
    }
}