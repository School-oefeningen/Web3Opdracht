package ui.controller;

import domain.db.DbException;
import domain.model.Person;
import util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Checker.isUserNotLoggedIn(request);

        try {
            String userId = request.getParameter("userId").trim();
            Person person = contactTracingService.getPersonService().get(userId);

            if (person != null && person.isCorrectPassword(request.getParameter("password").trim())) {
                Checker.loginUser(request, person, contactTracingService);
                request.setAttribute("success", "You have been logged in successfully.");
            } else {
                System.out.println("Log: someone tried to log in but entered a wrong password.");

                request.setAttribute("error", "Wrong password");
                request.setAttribute("userIdPrevious", userId);
            }
        } catch (DbException e) {
            System.out.println("Log: someone tried to log in but entered a wrong user id.");
            request.setAttribute("error", e.getMessage());
        }

        return "Controller?command=Home";
    }
}