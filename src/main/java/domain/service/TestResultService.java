package domain.service;

import domain.db.TestResultDBSQL;
import domain.model.DomainException;
import domain.model.TestResult;
import util.Checker;

import java.util.List;

public class TestResultService {
    private final TestResultDBSQL db = new TestResultDBSQL();

    public TestResultService() {}

    public TestResult getTestResultFromUser(String userId) {
        if (Checker.isEmptyString(userId)) throw new DomainException("No user id given");
        return db.getTestResultFromUser(userId);
    }

    public List<TestResult> getAll() {
        return db.getAll();
    }

    public void add(TestResult testResult) {
        // Exception if test result is null
        if (testResult == null) throw new DomainException("No testResult given");

        // Add test result
        db.add(testResult);
    }

    public void removeFromUser(String userId) {
        if (Checker.isEmptyString(userId)) throw new DomainException("No user id given");
        db.removeFromUser(userId);
    }
}