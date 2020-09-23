package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
    private Map<String, Person> persons = new HashMap<>();

    public PersonService() {
        Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
        Person person1 = new Person("person1", "lorenzo.catalano@student.ucll.be", "lorenzo1", "Lorenzo", "Catalano");
        add(administrator);
        add(person1);
    }

    public Person get(String personId) {
        if (personId == null) {
            throw new DbException("No id given");
        }
        Person person = persons.get(personId.toLowerCase());
        if (person == null) throw new DbException("Person not in database");
        return person;
    }

    public List<Person> getAll() {
        return new ArrayList<Person>(persons.values());
    }

    public void add(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }
        if (persons.containsKey(person.getUserid())) {
            throw new DbException("User already exists");
        }
        persons.put(person.getUserid(), person);
    }

    public void update(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }
        if (!persons.containsKey(person.getUserid())) {
            throw new DbException("No person found");
        }
        persons.put(person.getUserid(), person);
    }

    public void delete(String personId) {
        if (personId == null) {
            throw new DbException("No id given");
        }
        persons.remove(personId);
    }

    public int getNumberOfPersons() {
        return persons.size();
    }
}
