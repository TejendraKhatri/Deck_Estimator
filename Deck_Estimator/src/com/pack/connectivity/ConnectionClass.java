package com.pack.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public static Connection connection;

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/deckmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "p@ssw0rd";
        System.out.println("Loading driver.....");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find driver", e);
        }
        System.out.println("Driver Loaded");
        connection = null;
        System.out.println("Trying to connect to database...");
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to the database");
        return connection;
    }

    public static void closeConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
