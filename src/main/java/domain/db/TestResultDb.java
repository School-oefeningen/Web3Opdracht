package domain.db;

import domain.model.TestResult;

import java.util.List;

public interface TestResultDb {

    void add(TestResult testResult);

    boolean isUserPositive(String userId);

    List<TestResult> getAll();

    TestResult getTestResultFromUser(String userId);
}
