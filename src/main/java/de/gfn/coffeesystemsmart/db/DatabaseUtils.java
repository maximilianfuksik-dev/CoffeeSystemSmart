package de.gfn.coffeesystemsmart.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    private static final String DSN = "jdbc:sqlite:dbtest.db";

    private DatabaseUtils() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DSN);
    }

}
