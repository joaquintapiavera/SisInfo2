package com.example.sistemapostulantes.model;

import java.sql.*;

public class EstudianteCRUD {

    
    public static void crearEstudiante(Estudiante est) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, ci, correo, direccion, telefono, id_carrera, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, est.getNombre());
            ps.setString(2, est.getCi());
            ps.setString(3, est.getCorreo());
            ps.setString(4, est.getDireccion());
            ps.setString(5, est.getTelefono());
            ps.setInt(6, est.getIdCarrera());
            ps.setString(7, est.getContrasenia());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Crear estudiante falló, no se insertó ninguna fila.");
            }

            // Recuperar la clave primaria generada
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    est.setId(generatedId);
                    System.out.println("Estudiante insertado. id = " + generatedId);
                }
            }
        }
    }

    
    public static void actualizarEstudiante(Estudiante est) throws SQLException {
        String sql = "UPDATE Estudiante SET nombre=?, correo=?, direccion=?, telefono=?, id_carrera=?, contrasenia=? WHERE ci=?";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, est.getNombre());
            ps.setString(2, est.getCorreo());
            ps.setString(3, est.getDireccion());
            ps.setString(4, est.getTelefono());
            ps.setInt(5, est.getIdCarrera());
            ps.setString(6, est.getContrasenia());
            ps.setString(7, est.getCi());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se encontró estudiante con CI: " + est.getCi());
            }
        }
    }

    
    public static Estudiante obtenerPorCI(String ci) throws SQLException {
        String sql = "SELECT * FROM Estudiante WHERE ci = ?";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ci"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getInt("id_carrera"),
                        rs.getString("contrasenia")
                    );
                }
            }
        }
        return null;
    }

    
    public static Estudiante obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Estudiante WHERE id = ?";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ci"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getInt("id_carrera"),
                        rs.getString("contrasenia")
                    );
                }
            }
        }
        return null;
    }

    
    public static void eliminarEstudiante(String ci) throws SQLException {
        String sql = "DELETE FROM Estudiante WHERE ci = ?";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);
            int affected = ps.executeUpdate();

            if (affected == 0) {
                throw new SQLException("No se encontró estudiante con CI: " + ci);
            }
        }
    }
}

