package data;

import java.sql.*;

// https://github.com/xerial/sqlite-jdbc

public class DatabaseService {
    private static Connection connection = null;

    public static void start() {
        try {
            if (connection == null)
                connection = DriverManager.getConnection("jdbc:sqlite:cm2w.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    public static void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }




}
