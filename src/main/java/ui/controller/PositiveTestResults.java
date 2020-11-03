package ui.controller;

import domain.model.NotAuthorizedException;
import domain.model.TestResult;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PositiveTestResults extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Checker.roleIsAdmin(request);

        List<TestResult> testResults = testResultService.getAll();
        request.setAttribute("testResults", testResults);

        return "positiveTestResults.jsp";
    }
}