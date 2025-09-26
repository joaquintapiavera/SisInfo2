package com.example.sistemapostulantes.model;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection con = ConexionBaseDatos.getConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("✅ Conexión exitosa a la base de datos.");
            } else {
                System.out.println("❌ No se pudo establecer la conexión.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
