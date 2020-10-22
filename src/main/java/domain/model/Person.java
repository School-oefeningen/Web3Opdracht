package domain.model;

import util.Checker;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Person {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
    private String userid, email, password, firstName, lastName;
    private LocalDateTime registerDateTime = LocalDateTime.now();
    private LocalDateTime lastLoginDateTime = null;
    private int amountOfTimesLoggedIn = 0;

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

    public void incrementAmountOfTimesLoggedIn() {
        this.amountOfTimesLoggedIn++;
    }

    public int getAmountOfTimesLoggedIn() {
        return amountOfTimesLoggedIn;
    }

    public void setAmountOfTimesLoggedIn(int amountOfTimesLoggedIn) {
        if (amountOfTimesLoggedIn < 0) throw new DomainException("Amount of times logged in can't be negative");
        this.amountOfTimesLoggedIn = amountOfTimesLoggedIn;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public String getRegisterDateTimeToString() {
        return formatDateTimeToString(registerDateTime);
    }

    public void setRegisterDateTime(String registerDateTime) {
        if (Checker.isEmptyString(registerDateTime))
            throw new DomainException("Register date time can't be null");
        this.registerDateTime = formatStringToDateTime(registerDateTime);
    }

    public LocalDateTime getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public String getlastLoginDateTimeToString() {
        return formatDateTimeToString(lastLoginDateTime);
    }

    public void setLastLoginDateTime(String lastLoginDateTime) {
        if (lastLoginDateTime == null) this.lastLoginDateTime = null;
        else formatStringToDateTime(lastLoginDateTime);
    }

    public void setLastLoginDateTime() {
        this.lastLoginDateTime = LocalDateTime.now();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if (userid.isEmpty()) throw new DomainException("No user id given");
        this.userid = userid.toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Checker.isValidEmail(email)) this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (Checker.isEmptyString(password)) throw new DomainException("No password given");
        this.password = password;
    }

    public void setPasswordHashed(String password) {
        if (Checker.isEmptyString(password)) throw new DomainException("No password given");
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            //create MessageDigest
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            //reset
            crypt.reset();
            //update
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            //digest
            byte[] digest = crypt.digest();
            //convert to String
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            //return hashed password
            return digestAsBigInteger.toString(16);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new DomainException(e.getMessage());
        }
    }

    public boolean isCorrectPassword(String password) {
        if (Checker.isEmptyString(password)) throw new DomainException("No password given");
        return this.password.equals(hashPassword(password));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (Checker.isEmptyString(firstName)) throw new DomainException("No firstname given");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (Checker.isEmptyString(lastName)) throw new IllegalArgumentException("No last name given");
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

    private LocalDateTime formatStringToDateTime(String dateTimeString) {
        if (Checker.isEmptyString(dateTimeString)) throw new DomainException("Date time string can't be null");
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }
}