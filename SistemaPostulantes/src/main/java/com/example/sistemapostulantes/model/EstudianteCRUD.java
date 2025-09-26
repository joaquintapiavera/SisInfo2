package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstudianteCRUD {


    public static void crearEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO Usuario (nombre, ci, correo, direccion, telefono, id_carrera, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getCi());
            ps.setString(3, estudiante.getCorreo());
            ps.setString(4, estudiante.getDireccion());
            ps.setString(5, estudiante.getTelefono());
            ps.setInt(6, estudiante.getIdCarrera());
            ps.setString(7, estudiante.getContrasenia());

            ps.executeUpdate();

            System.out.println("Estudiante insertado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
