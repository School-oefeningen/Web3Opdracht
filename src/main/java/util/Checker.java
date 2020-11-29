package util;

import domain.model.DomainException;
import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Role;
import domain.service.ContactTracingService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    public static boolean isEmptyString(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (email.isEmpty()) throw new DomainException("No email given");
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) throw new DomainException("Email not valid");
        return true;
    }

    public static Person getUserInSession(HttpServletRequest request) {
        return (Person) request.getSession().getAttribute("user");
    }

    public static void isUserLoggedIn(HttpServletRequest request) {
        Person person = getUserInSession(request);
        if (person == null) {
            System.out.println("Log: someone tried to access content but was not logged in.");
            throw new DomainException("Please log in to see this content.");
        }
    }

    public static void checkRole(HttpServletRequest request, Role[] roles) {
        Person person = getUserInSession(request);
        if (person == null) {
            System.out.println("Log: tried to check role, but user is not logged in.");
            throw new NotAuthorizedException("You are not authorized to see this content!");
        }

        boolean allowed = false;
        for (Role role: roles) {
            if (person.getRole().equals(role)) {
                allowed = true;
                break;
            }
        }

        if (!allowed) {
            System.out.println("Log: someone tried to access content but isn't authorized.");
            throw new NotAuthorizedException("You are not authorized to see this content!");
        }
    }

    public static void roleIsAdmin(HttpServletRequest request) {
        Role[] roles =  {Role.ADMIN};
        checkRole(request, roles);
    }

    public static void roleIsNotAdmin(HttpServletRequest request) {
        Role[] roles =  {Role.USER};
        checkRole(request, roles);
    }

    public static void isUserNotLoggedIn(HttpServletRequest request) {
        Person person = getUserInSession(request);
        if (person != null) {
            System.out.println("Log: someone tried to access content but is already logged in.");
            throw new DomainException("You are not allowed to see this content because you are already logged in!");
        }
    }

    public static void loginUser(HttpServletRequest request, Person person, ContactTracingService contactTracingService) {
        Timestamp newLastLogin = new Timestamp(System.currentTimeMillis());
        person.setLastLogin(newLastLogin);
        person.incrementAmountOfTimesLoggedIn();

        contactTracingService.updatePerson(person);

        request.getSession().setAttribute("user", person);
    }

    public static void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}