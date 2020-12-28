package ui.controller;

import domain.db.DbException;
import domain.model.*;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdminSearch extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Checker.isUserLoggedIn(request);
        Checker.roleIsAdmin(request);

        Map<TestResult, List<Contact>> testResultsContactsMap = new LinkedHashMap<>();
        String userId = request.getParameter("userId");

        if (!Checker.isEmptyString(userId)) {
            try {
                Person person = contactTracingService.getPersonService().get(userId);
                TestResult testResult = contactTracingService.getTestResultService().getTestResultFromUser(userId);
                testResultsContactsMap.put(testResult, contactTracingService.getContactService().getAllFromUserAfterDate(testResult));
                request.setAttribute("testResultsContactsMap", testResultsContactsMap);
                return "adminSearch.jsp";
            } catch (DbException e) {
                request.setAttribute("error", e.getMessage());
            } catch (DomainException e) {
                request.setAttribute("error", "Person not tested positive");
            }
        }

        for (TestResult t: contactTracingService.getTestResultService().getAll()) {
            testResultsContactsMap.put(t, contactTracingService.getContactService().getAllFromUserAfterDate(t));
        }

        request.setAttribute("testResultsContactsMap", testResultsContactsMap);
        return "adminSearch.jsp";
    }
}