package domain.db;

import domain.model.Person;
import domain.model.Role;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        String sql = String.format("INSERT INTO %s.person(userid, email, password, firstname, lastname, registerdatetime, lastlogindatetime, amountoftimesloggedin, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, person.getUserid());
            statementSql.setString(2, person.getEmail());
            statementSql.setString(3, person.getPassword());
            statementSql.setString(4, person.getFirstName());
            statementSql.setString(5, person.getLastName());
            statementSql.setString(6, person.getRegisterDateTimeToString());
            statementSql.setString(7, person.getlastLoginDateTimeToString());
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
        String sql = String.format("SELECT * FROM %s.person WHERE userid != 'admin'", schema);

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

        String sql = String.format("UPDATE %s.person SET password = ?, lastlogindatetime = ?, amountoftimesloggedin = ? WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, person.getPassword());
            statementSql.setString(2, person.getlastLoginDateTimeToString());
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
        String registerDateTime = result.getString("registerdatetime");
        String lastLoginDateTime = result.getString("lastlogindatetime");
        int amountOfTimesLoggedIn = Integer.parseInt(result.getString("amountoftimesloggedin"));
        String roleString = result.getString("role");
        Role role = Role.valueOf(roleString);

        return new Person(userId, email, password, firstName, lastName, registerDateTime, lastLoginDateTime, amountOfTimesLoggedIn, role);
    }
}