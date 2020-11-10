package domain.model;

import util.Checker;

import java.sql.Date;
import java.time.LocalDate;

public class TestResult {
    private String userId;
    private Date date;

    public TestResult() {}

    public TestResult(String userId, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) throw new DomainException("Date can't be null");
        if (date.after(Date.valueOf(LocalDate.now()))) throw new DomainException("Date can't be after today");
        this.date = date;
    }
}
