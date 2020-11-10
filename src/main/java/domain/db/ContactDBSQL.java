package domain.db;

import domain.model.Contact;
import domain.model.TestResult;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDb {
    private final Connection connection;
    private final String schema;

    public ContactDBSQL() {
        connection = DbConnectionService.getDbConnection();
        schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }

    @Override
    public void add(Contact contact) {

        String sql = String.format("INSERT INTO %s.contact(userid, firstname, lastname, timestamp, phonenumber, email) VALUES (?, ?, ?, ?, ?, ?, ?);", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, contact.getUserId());
            statementSql.setString(2, contact.getFirstName());
            statementSql.setString(3, contact.getLastName());
            statementSql.setTimestamp(4, contact.getTimestamp());
            statementSql.setString(5, contact.getPhoneNumber());
            statementSql.setString(6, contact.getEmail());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAllFromUser(String userId) {
        List<Contact> contacts = new ArrayList<>();

        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ? ORDER BY lastname, firstname, timestamp", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Contact contact = makeContact(result);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return contacts;
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();

        String sql = String.format("SELECT * FROM %s.contact ORDER BY userid, lastname, firstname, timestamp", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Contact contact = makeContact(result);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return contacts;
    }

    @Override
    public List<Contact> getAllFromUserAfterDate(TestResult testResult) {

        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ?" +
                "AND timestamp >= ? " +
                "ORDER BY lastname, firstname, timestamp", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, testResult.getUserId());
            statementSql.setDate(2, testResult.getDate());
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Contact contact = makeContact(result);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return contacts;
    }

    @Override
    public void removeFromUser(String userId) {

        String sql = String.format("DELETE FROM %s.contact WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private Contact makeContact(ResultSet result) throws SQLException {
        String userId = result.getString("userid");
        String firstName = result.getString("firstname");
        String lastName = result.getString("lastname");

        Timestamp timestamp = result.getTimestamp("timestamp");

        String phonenumber = result.getString("phonenumber");
        String email = result.getString("email");

        return new Contact(userId, firstName, lastName, timestamp, phonenumber, email);
    }
}