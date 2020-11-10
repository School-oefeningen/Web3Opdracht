package domain.model;

import util.Checker;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class Person {
    private String userid, email, password, firstName, lastName;
    private Timestamp register = new Timestamp(System.currentTimeMillis());
    private Timestamp lastLogin = null;
    private int amountOfTimesLoggedIn = 0;
    private Role role;

    public Person() {
    }

    public Person(String userid, String email, String password, String firstName, String lastName, Timestamp register, Timestamp lastLogin, int amountOfTimesLoggedIn, Role role) {
        setUserid(userid);
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setRegister(register);
        setLastLogin(lastLogin);
        setAmountOfTimesLoggedIn(amountOfTimesLoggedIn);
        setRole(role);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (role == null) throw new DomainException("Role can't be null");
        this.role = role;
    }

    public void incrementAmountOfTimesLoggedIn() {
        this.amountOfTimesLoggedIn++;
    }

    public int getAmountOfTimesLoggedIn() {
        return amountOfTimesLoggedIn;
    }

    public void setAmountOfTimesLoggedIn(int amountOfTimesLoggedIn) {
        if (amountOfTimesLoggedIn < 0) throw new DomainException("Amount of times logged in can't be negative");
        this.amountOfTimesLoggedIn = amountOfTimesLoggedIn;
    }

    public Timestamp getRegister() {
        return register;
    }

    public void setRegister(Timestamp register) {
        if (register == null) throw new DomainException("Register can't be null");
        this.register = register;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        if (lastLogin == null) this.lastLogin = null;
        this.lastLogin = lastLogin;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if (userid.isEmpty()) throw new DomainException("No user id given");
        this.userid = userid.toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Checker.isValidEmail(email)) this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (Checker.isEmptyString(password)) throw new DomainException("No password given");
        this.password = password;
    }

    public void setPasswordHashed(String password) {
        if (Checker.isEmptyString(password)) throw new DomainException("No password given");
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            //create MessageDigest
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            //reset
            crypt.reset();
            //update
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            //digest
            byte[] digest = crypt.digest();
            //convert to String
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            //return hashed password
            return digestAsBigInteger.toString(16);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new DomainException(e.getMessage());
        }
    }

    public boolean isCorrectPassword(String password) {
        if (Checker.isEmptyString(password)) throw new DomainException("No password given");
        return this.password.equals(hashPassword(password));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (Checker.isEmptyString(firstName)) throw new DomainException("No firstname given");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (Checker.isEmptyString(lastName)) throw new IllegalArgumentException("No last name given");
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }
}