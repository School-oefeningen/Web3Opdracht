package domain.model;

import util.Checker;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Contact {
    private String userId, firstName, lastName, phoneNumber, email;
    private Timestamp timestamp;

    public Contact() {}

    public Contact(String userId, String firstName, String lastName, Timestamp timestamp, String phonenumber, String email) {
        setUserid(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setTimestamp(timestamp);
        setPhoneNumber(phonenumber);
        setEmail(email);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserid(String userId) {
        if (Checker.isEmptyString(userId)) throw new DomainException("No user id given");
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (Checker.isEmptyString(firstName)) throw new DomainException("No first name given");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (Checker.isEmptyString(lastName)) throw new IllegalArgumentException("No last name given");
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (Checker.isEmptyString(phoneNumber)) throw new DomainException("No phone number given");
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Checker.isValidEmail(email)) this.email = email;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        if (timestamp == null) throw new DomainException("No date or hour given");
        if (!timestamp.before(Date.valueOf(LocalDate.now()))) throw new DomainException("Date can't be after today");
        this.timestamp = timestamp;
    }
}
