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
            Person person = getService().get(userId);

            if (person != null && person.isCorrectPassword(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", person);
            } else {
                request.setAttribute("fout", "Het passwoord is fout");
                request.setAttribute("userIdPrevious", userId);
            }
        } catch (DbException e) {
            request.setAttribute("fout", "Het user id is niet in de database");
        }

        return "/Controller?command=Home";
    }
}
