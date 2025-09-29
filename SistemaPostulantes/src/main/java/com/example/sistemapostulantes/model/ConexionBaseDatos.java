package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {


    private static final String URL =
            "jdbc:mysql://aiven:AVNS_vCEsPI01PAqiYXKiTbl@mysql-sisinfo2.d.aivencloud.com:11329/Postulantes?ssl-mode=REQUIRED";
    private static final String USUARIO = "aiven";
    private static final String CONTRASENIA = "AVNS_vCEsPI01PAqiYXKiTbl";

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado", e);
        }
        return DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
    }
}
