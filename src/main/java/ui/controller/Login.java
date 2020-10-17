package ui.controller;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        try {
            String userId = request.getParameter("userId").trim();
            Person person = service.get(userId);

            if (person != null && person.isCorrectPassword(request.getParameter("password").trim())) {
                person.setLastLoginDateTime();
                person.incrementAmountOfTimesLoggedIn();
                service.update(person);

                HttpSession session = request.getSession();
                session.setAttribute("user", person);
            } else {
                request.setAttribute("error", "Wrong password");
                request.setAttribute("userIdPrevious", userId);
            }
        } catch (DbException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "Controller?command=Home";
    }
}
