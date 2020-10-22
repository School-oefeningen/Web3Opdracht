package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
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
        setContactDate(contact, request, errors);
        setContactHour(contact, request, errors);
        setContactPhoneNumber(contact, request, errors);
        setContactEmail(contact, request, errors);

        if (errors.size() == 0) {
            try {
                contactService.add(contact);
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
            request.setAttribute("userIdPrevious", userId);
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

    private void setContactDate(Contact contact, HttpServletRequest request, List<String> errors) {
        String dateString = request.getParameter("date").trim();
        try {
            contact.setDate(LocalDate.parse(dateString));
            request.setAttribute("datePrevious", dateString);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactHour(Contact contact, HttpServletRequest request, List<String> errors) {
        String hourString = request.getParameter("hour").trim();
        try {
            contact.setHour(LocalTime.parse(hourString));
            request.setAttribute("hourPrevious", hourString);
        } catch (Exception e) {
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
