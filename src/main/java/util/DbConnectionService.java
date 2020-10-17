package util;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionService {
    private static Connection dbConnection;
    private static String schema;

    public static String getSchema() {
        return schema;
    }

    public static Connection getDbConnection() {
        return dbConnection;
    }

    public static void connect(String dbURL, String searchPath) {
        schema = searchPath;
        DBConnectionManager connectionManager = DBConnectionManager.getInstance(dbURL);
        dbConnection = connectionManager.getConnection();
    }

    public static void disconnect() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}