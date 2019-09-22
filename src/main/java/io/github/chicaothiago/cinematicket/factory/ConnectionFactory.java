package io.github.chicaothiago.cinematicket.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection get() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/cinema_tickets",
                "root",
                "root"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
