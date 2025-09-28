package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidarInscripcion {

    public static String obtenerEstadoInscripcion(int idEstudiante) {
        String sql = "SELECT estado FROM Inscripcion WHERE id_estudiante = ?";
        String estado = "No encontrado";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEstudiante);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    estado = rs.getString("estado");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return estado;
    }

    public static boolean validarEstado(int idEstudiante) {
        String estado = obtenerEstadoInscripcion(idEstudiante);

        switch (estado.toLowerCase()) {
            case "pendiente":
                System.out.println("El estudiante aún no completó el proceso de inscripción.");
                return false;

            case "aprobado":
                System.out.println("El estudiante ya está inscrito correctamente.");
                return true;

            case "rechazado":
                System.out.println("La inscripción fue rechazada. Debe corregir sus datos.");
                return false;

            default:
                System.out.println("No se encontró información de inscripción para este estudiante.");
                return false;
        }
    }
}

