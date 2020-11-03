package domain.model;

import util.Checker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestResult {
    private String userId;
    private LocalDate date;

    public TestResult() {}

    public TestResult(String userId, LocalDate date) {
        setUserId(userId);
        setDate(date);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (Checker.isEmptyString(userId)) throw new DomainException("User id can't be null");
        this.userId = userId;
    }

    public String getDateAsString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) throw new DomainException("Date can't be null");
        if ((date.isAfter(LocalDate.now()))) throw new DomainException("Date can't be after today");
        this.date = date;
    }
}
