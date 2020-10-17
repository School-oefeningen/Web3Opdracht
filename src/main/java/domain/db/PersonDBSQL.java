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
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    /**
     * Stores the given person in the database
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(Person person) {

        String sql = String.format("INSERT INTO %s.person(userid, email, password, firstname, lastname, registerdatetime, lastlogindatetime, amountoftimesloggedin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getEmail());
            statementSQL.setString(3, person.getPassword());
            statementSQL.setString(4, person.getFirstName());
            statementSQL.setString(5, person.getLastName());
            statementSQL.setString(6, person.getRegisterDateTimeToString());
            statementSQL.setString(7, person.getlastLoginDateTimeToString());
            statementSQL.setInt(8, person.getAmountOfTimesLoggedIn());
            statementSQL.execute();
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
        String sql = String.format("SELECT * FROM %s.person", this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userId = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String registerDateTime = result.getString("registerdatetime");
                String lastLoginDateTime = result.getString("lastlogindatetime");
                int amountOfTimesLoggedIn = Integer.parseInt(result.getString("amountoftimesloggedin"));

                Person person = new Person(userId, email, password, firstName, lastName, registerDateTime, lastLoginDateTime, amountOfTimesLoggedIn);
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

        String sql = String.format("DELETE FROM %s.person WHERE userid = ?", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, personId);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}