package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {
    public Connection connection;
    private static DBConnectionManager dbConnectionManager_instance = null;

    private DBConnectionManager(String dbURL) {
        Properties dbProperties = new Properties();
        try {
            Class.forName("util.Secret");  // implementation of abstract class Credentials
            Secret.setPass(dbProperties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Secret with credentials not found!");
        }
        dbProperties.setProperty("ssl", "true");
        dbProperties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        //dbProperties.setProperty("sslmode", "true");
        dbProperties.setProperty("sslmode", "require");

        try {
            System.out.print("connecting to database ...");
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(dbURL, dbProperties);
            System.out.println("done");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException().getMessage());
        } catch (SQLException e) {
            System.out.println("connection troubles");
            System.out.println(e.getErrorCode());
        }
    }

    public static DBConnectionManager getInstance(String dbURL) {
        if (dbConnectionManager_instance == null) {
            dbConnectionManager_instance = new DBConnectionManager(dbURL);
        }
        return dbConnectionManager_instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

}