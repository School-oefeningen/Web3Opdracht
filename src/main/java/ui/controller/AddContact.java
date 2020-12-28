package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AddContact extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();

        Contact contact = new Contact();
        setContactUserId(contact, request, errors);
        setContactFirstName(contact, request, errors);
        setContactLastName(contact, request, errors);
        setContactTimestamp(contact, request, errors);
        setContactPhoneNumber(contact, request, errors);
        setContactEmail(contact, request, errors);

        if (errors.size() == 0) {
            try {
                contactTracingService.getContactService().add(contact);
                request.setAttribute("success", "Your contact has been registered successfully.");
                return "Controller?command=ContactsOverview";
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=AddContactForm";
    }

    private void setContactUserId(Contact contact, HttpServletRequest request, List<String> errors) {
        Person person = (Person) request.getSession().getAttribute("user");
        String userId = person.getUserid();
        try {
            contact.setUserid(userId);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactFirstName(Contact contact, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName").trim();
        try {
            contact.setFirstName(firstName);
            request.setAttribute("firstNamePrevious", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactLastName(Contact contact, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName").trim();
        try {
            contact.setLastName(lastName);
            request.setAttribute("lastNamePrevious", lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactTimestamp(Contact contact, HttpServletRequest request, List<String> errors) {
        String dateString = request.getParameter("date").trim();
        String hourString = request.getParameter("hour").trim();
        LocalDate date = null;
        LocalTime hour = null;

        try {
            date = LocalDate.parse(dateString);
            request.setAttribute("datePrevious", dateString);
        } catch (DateTimeParseException e) {
            errors.add("No valid date given");
        }

        try {
            hour = LocalTime.parse(hourString);
            request.setAttribute("hourPrevious", hourString);
        } catch (DateTimeParseException e) {
            errors.add("No valid hour given");
        }

        try {
            if (date != null && hour != null) {
                LocalDateTime dateTime = LocalDateTime.of(date, hour);
                Timestamp timestamp = Timestamp.valueOf(dateTime);
                contact.setTimestamp(timestamp);
            }
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactPhoneNumber(Contact contact, HttpServletRequest request, List<String> errors) {
        String phoneNumber = request.getParameter("phoneNumber").trim();
        try {
            contact.setPhoneNumber(phoneNumber);
            request.setAttribute("phoneNumberPrevious", phoneNumber);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactEmail(Contact contact, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try {
            contact.setEmail(email);
            request.setAttribute("emailPrevious", email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
