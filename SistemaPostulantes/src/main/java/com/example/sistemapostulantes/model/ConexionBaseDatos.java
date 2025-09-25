package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBaseDatos {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://avnadmin:AVNS_l0YRyO-laQz0uaAmDF2@mysql-sisinfo2.d.aivencloud.com:11329/Postulantes?ssl-mode=REQUIRED";
    private static final String USUARIO = "avnadmin";
    private static final String CONTRASENIA = "AVNS_l0YRyO-laQz0uaAmDF2";

    static {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConexion() {
        return connection;
    }
}
