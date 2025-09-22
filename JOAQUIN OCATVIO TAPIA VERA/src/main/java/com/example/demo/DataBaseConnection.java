package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://avnadmin:AVNS_l0YRyO-laQz0uaAmDF2@mysql-sisinfo2.d.aivencloud.com:11329/Registro?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_l0YRyO-laQz0uaAmDF2";

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
