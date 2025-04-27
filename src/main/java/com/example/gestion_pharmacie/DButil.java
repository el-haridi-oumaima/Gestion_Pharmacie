package com.example.gestion_pharmacie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DButil {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gestionpharmacie", "root", "admin"); // change password if needed
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found!", e);
        }
    }
}
