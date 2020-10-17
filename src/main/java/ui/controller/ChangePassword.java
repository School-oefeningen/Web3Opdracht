package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("yes") == null) {
            return "Controller?command=Home";
        }

        String newPassword = request.getParameter("newPassword");
        String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");

        Person p = (Person) request.getSession().getAttribute("user");
        Person person = service.get(p.getUserid());

        person.setPasswordHashed(newPassword);
        service.update(person);

        request.getSession().invalidate();

        request.setAttribute("succes", "Your password has been changed! Please log in again.");
        return "Controller?command=Home";
    }
}
