package br.ufrn.lojaonline.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lojaonline",
                "root",
                "6901"
        );
    }
}
