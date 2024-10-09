package com.huynhhoapy97.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private Connection connection;

    public Connection createConnectionToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/supplementstore";
            String user = "root";
            String password = "Hoadeptraipy@97";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
