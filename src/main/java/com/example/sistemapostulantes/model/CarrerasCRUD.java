package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarrerasCRUD {


    public static void crearCarrera(Carrera carrera) {


        String sql = "INSERT INTO Carrera (nombre_carrera) VALUES (?)";


        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(1, carrera.getNombre());

            ps.executeUpdate(); // Ejecutamos la inserción

            System.out.println("Carrera insertada correctamente.");

        } catch (SQLException e) {

            System.err.println("Error al insertar la carrera:");
            e.printStackTrace();
        }
    }
}
