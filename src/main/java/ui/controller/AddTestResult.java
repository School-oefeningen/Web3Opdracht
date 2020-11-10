package ui.controller;

import domain.db.DbException;
import domain.model.Person;
import domain.model.TestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddTestResult extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();

        TestResult testResult = new TestResult();
        setTestResultUserId(testResult, request, errors);
        setTestResultDate(testResult, request, errors);

        if (errors.size() == 0) {
            try {
                testResultService.add(testResult);
                return "Controller?command=TestResultsSearch";
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=RegisterTestResult";
    }

    private void setTestResultUserId(TestResult testResult, HttpServletRequest request, List<String> errors) {
        Person person = (Person) request.getSession().getAttribute("user");
        String userId = person.getUserid();
        try {
            testResult.setUserId(userId);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setTestResultDate(TestResult testResult, HttpServletRequest request, List<String> errors) {
        String dateString = request.getParameter("date").trim();
        try {
            Date date = Date.valueOf(LocalDate.parse(dateString));
            testResult.setDate(date);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}