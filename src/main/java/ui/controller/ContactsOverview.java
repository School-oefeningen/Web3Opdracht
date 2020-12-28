package ui.controller;

import domain.model.Contact;
import domain.model.Person;
import domain.model.Role;
import domain.model.TestResult;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class ContactsOverview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserLoggedIn(request);
        Person person = Checker.getUserInSession(request);
        List<Contact> contacts;
        LocalDate fromDate = null;
        LocalDate untilDate = null;

        try {
            String fromString = request.getParameter("fromDate");
            fromDate = LocalDate.parse(fromString);
            request.setAttribute("fromDatePrevious", fromString);

            String untilString = request.getParameter("untilDate");
            untilDate = LocalDate.parse(untilString);
            request.setAttribute("untilDatePrevious", untilString);
        } catch (Exception ignored) {}

        if (fromDate != null && untilDate != null && !fromDate.isAfter(untilDate)) {
            if (person.getRole() == Role.ADMIN) {
                contacts = contactTracingService.getContactService().getAllContactsBetweenDates(fromDate, untilDate);
            } else {
                contacts = contactTracingService.getContactService().getAllContactsFromUserBetweenDates(person, fromDate, untilDate);
            }
        } else {
            if (person.getRole() == Role.ADMIN) {
                contacts = contactTracingService.getContactService().getAll();
            }
            else {
                contacts = contactTracingService.getContactService().getAllFromUser(person.getUserid());
            }
        }
        request.setAttribute("contacts", contacts);

        TestResult testResult = contactTracingService.getTestResultService().getTestResultFromUser(person.getUserid());
        if (testResult != null) {
            request.setAttribute("testResult", testResult);
        }

        return "contactsOverview.jsp";
    }
}