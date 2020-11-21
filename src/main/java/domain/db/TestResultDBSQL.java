package domain.db;

import domain.model.TestResult;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestResultDBSQL implements TestResultDb {
    private final Connection connection;
    private final String schema;

    public TestResultDBSQL() {
        connection = DbConnectionService.getDbConnection();
        schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }

    @Override
    public void add(TestResult testResult) {

        String sql = String.format("INSERT INTO %s.testresult(userid, date) VALUES (?, ?) ON CONFLICT (userid) DO UPDATE SET date = excluded.date;", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, testResult.getUserId());
            statementSql.setDate(2, testResult.getDate());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<TestResult> getAll() {

        List<TestResult> testResults = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.testresult ORDER BY date, userid", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                TestResult testResult = makeTestResult(result);
                testResults.add(testResult);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return testResults;
    }

    @Override
    public TestResult getTestResultFromUser(String userId) {

        String sql = String.format("SELECT * FROM %s.testresult WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            ResultSet result = statementSql.executeQuery();

            if (result.next()) return makeTestResult(result);
            else return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void removeFromUser(String userId) {

        String sql = String.format("DELETE FROM %s.testresult WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private TestResult makeTestResult(ResultSet result) throws SQLException {
        String userId = result.getString("userid");
        Date date = result.getDate("date");

        return new TestResult(userId, date);
    }
}