package util;

import domain.model.DomainException;

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
}
