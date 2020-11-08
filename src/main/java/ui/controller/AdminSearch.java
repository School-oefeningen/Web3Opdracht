package ui.controller;

import domain.model.Contact;
import domain.model.NotAuthorizedException;
import domain.model.TestResult;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminSearch extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Checker.isUserLoggedIn(request);
        Checker.roleIsAdmin(request);

        Map<TestResult, List<Contact>> testResultsContactsMap = new HashMap<>();

        List<TestResult> testResults = testResultService.getAll();
        for (TestResult t: testResults) {
            testResultsContactsMap.put(t, contactService.getAllFromUserAfterDate(t));
        }

        System.out.println(testResultsContactsMap);

        request.setAttribute("testResultsContactsMap", testResultsContactsMap);
        return "adminSearch.jsp";
    }
}