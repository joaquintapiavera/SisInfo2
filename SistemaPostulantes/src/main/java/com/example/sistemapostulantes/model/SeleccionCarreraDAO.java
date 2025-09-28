package com.example.sistemapostulantes.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
public class SeleccionCarreraDAO {
    public static void guardarSeleccion(SeleccionCarrera seleccion) {
        String sql = "INSERT INTO seleccion_carrera (estudiante_id, carrera_id, fecha_seleccion) VALUES (?, ?, ?)";
        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, seleccion.getEstudianteId());
            ps.setInt(2, seleccion.getCarreraId());
            ps.setTimestamp(3, Timestamp.valueOf(seleccion.getFechaSeleccion()));
            ps.executeUpdate();
            System.out.println("Selección de carrera guardada con fecha y hora.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
