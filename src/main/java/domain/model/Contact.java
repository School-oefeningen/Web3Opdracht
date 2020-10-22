package domain.model;

import util.Checker;

import java.time.LocalDate;
import java.time.LocalTime;

public class Contact {
    private String userId, firstName, lastName, phoneNumber, email;
    private LocalDate date;
    private LocalTime hour;

    public Contact() {}

    public Contact(String userId, String firstName, String lastName, LocalDate date, LocalTime hour, String phonenumber, String email) {
        setUserid(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setDate(date);
        setHour(hour);
        setPhoneNumber(phonenumber);
        setEmail(email);
    }

    public String getUserid() {
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

    public String getDateString() {
        return date.toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) throw new DomainException("No date given");
        this.date = date;
    }

    public String getHourString() {
        return hour.toString();
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        if (hour == null) throw new DomainException("No hour given");
        this.hour = hour;
    }
}
