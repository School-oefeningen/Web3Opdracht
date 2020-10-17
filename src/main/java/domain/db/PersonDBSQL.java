package domain.db;

import domain.model.Person;
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

    /**
     * Stores the given person in the database
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(Person person) {

        String sql = String.format("INSERT INTO %s.person(userid, email, password, firstname, lastname, registerdatetime, lastlogindatetime, amountoftimesloggedin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", schema);

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
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    /**
     * Returns a list with all people stored in the database
     * @return An arraylist with all people stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    @Override
    public List<Person> getAll() {

        List<Person> people = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person", schema);

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

    /**
     * Removes a person from the database
     * @param personId The id of the person
     */
    @Override
    public void remove(String personId) {

        String sql = String.format("DELETE FROM %s.person WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public boolean personInDb(String personId) {

        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
            ResultSet result = statementSql.executeQuery();

            return result.next();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Person get(String personId) {

        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personId);
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

        return new Person(userId, email, password, firstName, lastName, registerDateTime, lastLoginDateTime, amountOfTimesLoggedIn);
    }
}