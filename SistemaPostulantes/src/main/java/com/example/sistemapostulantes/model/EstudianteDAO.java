package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstudianteDAO {

    
    public static Estudiante obtenerPorCI(String ci) {
        Estudiante estudiante = null;

        String sql = "SELECT * FROM Usuario WHERE ci = ?";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                estudiante = new Estudiante(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("ci"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getInt("id_carrera"),
                        rs.getString("contrasenia")
                );
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return estudiante;
    }
}

