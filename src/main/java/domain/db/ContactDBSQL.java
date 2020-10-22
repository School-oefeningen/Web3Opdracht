package domain.db;

import domain.model.Contact;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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

        String sql = String.format("INSERT INTO %s.contact(userid, firstname, lastname, date, hour, phonenumber, email) VALUES (?, ?, ?, ?, ?, ?, ?);", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, contact.getUserid());
            statementSql.setString(2, contact.getFirstName());
            statementSql.setString(3, contact.getLastName());
            statementSql.setString(4, contact.getDate().toString());
            statementSql.setString(5, contact.getHour().toString());
            statementSql.setString(6, contact.getPhoneNumber());
            statementSql.setString(7, contact.getEmail());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAllFromUser(String userId) {

        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ? ORDER BY lastname, firstname, date, hour", schema);

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
        String sql = String.format("SELECT * FROM %s.contact ORDER BY lastname, firstname, date, hour", schema);

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

    private Contact makeContact(ResultSet result) throws SQLException {

        String userId = result.getString("userid");
        String firstName = result.getString("firstname");
        String lastName = result.getString("lastname");
        String dateString = result.getString("date");
        LocalDate date = LocalDate.parse(dateString);
        String hourString = result.getString("hour");
        LocalTime hour = LocalTime.parse(hourString);
        String phonenumber = result.getString("phonenumber");
        String email = result.getString("email");

        return new Contact(userId, firstName, lastName, date, hour, phonenumber, email);
    }
}