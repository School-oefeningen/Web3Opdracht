package domain.db;

import domain.model.TestResult;

import java.util.List;

public interface TestResultDb {

    void add(TestResult testResult);

    List<TestResult> getAll();

    TestResult getTestResultFromUser(String userId);

    void removeFromUser(String userId);
}