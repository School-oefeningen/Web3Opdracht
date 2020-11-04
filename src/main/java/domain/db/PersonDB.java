package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {

    /**
     * Stores the given person in the database
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void add(Person person);

    /**
     * Returns a list with all people stored in the database
     * @return An arraylist with all people stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    List<Person> getAll();

    /**
     * Removes a person from the database
     * @param userId The id of the person
     */
    void remove(String userId);

    void update(Person person);

    /**
     * Checks if person is in the database
     * @param userId The id of the person
     * @return True if person in database, else false
     */
    boolean isPersonInDb(String userId);

    /**
     * Returns person given its id
     * @param userId The id of the person
     * @return Person
     */
    Person get(String userId);
}