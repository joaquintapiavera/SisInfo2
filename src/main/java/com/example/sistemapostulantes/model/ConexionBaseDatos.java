package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://mysql-sisinfo2.d.aivencloud.com:11329/Postulantes?ssl-mode=REQUIRED";
    private static final String USUARIO = "avnadmin";
    private static final String CONTRASENIA = "AVNS_l0YRyO-laQz0uaAmDF2";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
    }
}
