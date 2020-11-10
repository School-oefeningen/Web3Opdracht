package domain.db;

import domain.model.Person;
import domain.model.Role;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private final Connection connection;
    private final String schema;

    public PersonDBSQL() {
        connection = DbConnectionService.getDbConnection();
        schema = DbConnectionService.getSchema();
        System.out.println("Schema: " + schema);
    }

    @Override
    public void add(Person person) {

        String sql = String.format("INSERT INTO %s.person(userid, email, password, firstname, lastname, register, lastlogin, amountoftimesloggedin, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, person.getUserid());
            statementSql.setString(2, person.getEmail());
            statementSql.setString(3, person.getPassword());
            statementSql.setString(4, person.getFirstName());
            statementSql.setString(5, person.getLastName());
            statementSql.setTimestamp(6, person.getRegister());
            statementSql.setTimestamp(7, person.getLastLogin());
            statementSql.setInt(8, person.getAmountOfTimesLoggedIn());
            statementSql.setString(9, person.getRole().toString());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Person> getAll() {

        List<Person> people = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person WHERE role != 'ADMIN'", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {

                Person person = makePerson(result);
                people.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return people;
    }

    @Override
    public void remove(String userId) {

        String sql = String.format("DELETE FROM %s.person WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Person person) {

        String sql = String.format("UPDATE %s.person SET password = ?, lastlogin = ?, amountoftimesloggedin = ? WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, person.getPassword());
            statementSql.setTimestamp(2, person.getLastLogin());
            statementSql.setInt(3, person.getAmountOfTimesLoggedIn());
            statementSql.setString(4, person.getUserid());
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean isPersonInDb(String userId) {

        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            ResultSet result = statementSql.executeQuery();

            return result.next();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Person get(String userId) {

        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userId);
            ResultSet result = statementSql.executeQuery();
            result.next();

            return makePerson(result);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    private Person makePerson(ResultSet result) throws SQLException {
        String userId = result.getString("userid");
        String email = result.getString("email");
        String password = result.getString("password");
        String firstName = result.getString("firstname");
        String lastName = result.getString("lastname");

        Timestamp register = result.getTimestamp("register");
        Timestamp lastLogin = result.getTimestamp("lastlogin");

        int amountOfTimesLoggedIn = Integer.parseInt(result.getString("amountoftimesloggedin"));

        String roleString = result.getString("role");
        Role role = Role.valueOf(roleString);

        return new Person(userId, email, password, firstName, lastName, register, lastLogin, amountOfTimesLoggedIn, role);
    }
}