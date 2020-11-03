package util;

import domain.model.DomainException;
import domain.model.NotAuthorizedException;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
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

    public static void checkRole(HttpServletRequest request, Role[] roles) {
        Person person = (Person) request.getSession().getAttribute("user");
        if (person == null) throw new NotAuthorizedException("You are not authorized to see this content!");

        boolean allowed = false;
        for (Role r: roles) {
            if (person.getRole().equals(r)) {
                allowed = true;
                break;
            }
        }

        if (!allowed) throw new NotAuthorizedException("You are not authorized to see this content!");
    }

    public static void roleIsAdmin(HttpServletRequest request) {
        Role[] roles =  {Role.ADMIN};
        checkRole(request, roles);
    }
}