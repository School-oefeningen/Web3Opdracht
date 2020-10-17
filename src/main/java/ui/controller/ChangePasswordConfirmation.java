package ui.controller;

import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordConfirmation extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");

        Person p = (Person) request.getSession().getAttribute("user");

        try {
            // Checks
            isCorrectPassword(p, currentPassword);
            newPasswordAndConfirmationMatch(newPassword, newPasswordConfirmation);
            currentPasswordAndNewPasswordDontMatch(currentPassword, newPassword);

            // If all checks passed
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("newPasswordConfirmation", newPasswordConfirmation);
            return "changePasswordConfirmation.jsp";
        } catch (DomainException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "Controller?command=ChangePasswordForm";
    }

    private void isCorrectPassword(Person p, String currentPassword) {
        if (!p.isCorrectPassword(currentPassword)) throw new DomainException("Wrong password");
    }

    private void newPasswordAndConfirmationMatch(String newPassword, String newPasswordConfirmation) {
        if (!newPassword.equals(newPasswordConfirmation))
            throw new DomainException("New password and new password confirmation don't match");
    }

    private void currentPasswordAndNewPasswordDontMatch(String currentPassword, String newPassword) {
        if (currentPassword.equals(newPassword))
            throw new DomainException("Current password and new password can't be the same");
    }
}