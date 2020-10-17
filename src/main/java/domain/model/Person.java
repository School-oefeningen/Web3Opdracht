package domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String userid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime registerDateTime = LocalDateTime.now();
    private LocalDateTime lastLoginDateTime = null;
    private int amountOfTimesLoggedIn = 0;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");

    public Person() {
    }

    public Person(String userid, String email, String password, String firstName, String lastName, String registerDateTime, String lastLoginDateTime, int amountOfTimesLoggedIn) {
        setUserid(userid);
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setRegisterDateTime(registerDateTime);
        setLastLoginDateTime(lastLoginDateTime);
        setAmountOfTimesLoggedIn(amountOfTimesLoggedIn);
    }

    public void setRegisterDateTime(String registerDateTime) {
        if (registerDateTime == null || registerDateTime.trim().isEmpty())
            throw new DomainException("Register date time can't be null");
        this.registerDateTime = formatStringToDateTime(registerDateTime);
    }

    public void setLastLoginDateTime(String lastLoginDateTime) {
        if (lastLoginDateTime == null) this.lastLoginDateTime = null;
        else formatStringToDateTime(lastLoginDateTime);
    }

    public void setAmountOfTimesLoggedIn(int amountOfTimesLoggedIn) {
        if (amountOfTimesLoggedIn < 0) throw new DomainException("Amount of times logged in can't be negative");
        this.amountOfTimesLoggedIn = amountOfTimesLoggedIn;
    }

    public int getAmountOfTimesLoggedIn() {
        return amountOfTimesLoggedIn;
    }

    public void incrementAmountOfTimesLoggedIn() {
        this.amountOfTimesLoggedIn++;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public String getRegisterDateTimeToString() {
        return formatDateTimeToString(registerDateTime);
    }

    public LocalDateTime getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public String getlastLoginDateTimeToString() {
        return formatDateTimeToString(lastLoginDateTime);
    }

    public void setLastLoginDateTime() {
        this.lastLoginDateTime = LocalDateTime.now();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if (userid.isEmpty()) {
            throw new IllegalArgumentException("No user id given");
        }
        this.userid = userid.toLowerCase();
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        return getPassword().equals(password);
    }

    public void setPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }

    private String formatDateTimeToString(LocalDateTime ldt) {
        if (ldt == null) return null;
        return ldt.format(FORMATTER);
    }

    private LocalDateTime formatStringToDateTime(String s) {
        if (s == null || s.trim().isEmpty()) throw new DomainException("Date time string can't be null");
        return LocalDateTime.parse(s, FORMATTER);
    }
}