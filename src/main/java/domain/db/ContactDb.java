package domain.db;

import domain.model.Contact;
import domain.model.TestResult;

import java.util.List;

public interface ContactDb {

    /**
     * Stores the given contact in the database
     * @param contact The contact to be added
     * @throws DbException if the given contact is null
     * @throws DbException if the given contact can not be added
     */
    void add(Contact contact);

    /**
     * Returns a list with all contacts of a certain person stored in the database
     * @return An arraylist with all contacts of a certain stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    List<Contact> getAllFromUser(String userId);

    /**
     * Returns a list with all contacts stored in the database
     * @return An arraylist with all contacts stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    List<Contact> getAll();

    /**
     * Returns a list with all contacts of a certain person after a certain date stored in the database
     * @return An arraylist with all contacts of a certain person after a certain date stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    List<Contact> getAllFromUserAfterDate(TestResult testResult);

    void removeFromUser(String userId);
}