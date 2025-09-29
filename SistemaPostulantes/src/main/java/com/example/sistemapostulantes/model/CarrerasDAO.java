package com.example.sistemapostulantes.model;

import com.example.sistemapostulantes.controller.ControladorCarrera.CarreraDAOView;
import java.sql.*;
import java.util.*;

public class CarrerasDAO {

    public static List<CarreraDAOView> listarCarrerasConFacultadTipo() {
        List<CarreraDAOView> out = new ArrayList<>();
        String sql =
                "SELECT c.id_carrera, c.codigo_carrera, c.nombre_carrera, " +
                        "       COALESCE(f.nombre_corto,'') AS facultad, " +
                        "       COALESCE(c.tipo,'Carrera') AS tipo " +
                        "FROM Carrera c " +
                        "LEFT JOIN Facultad f ON f.id_facultad = c.id_facultad " +
                        "ORDER BY f.nombre_corto ASC, c.nombre_carrera ASC";
        try (Connection cn = ConexionBaseDatos.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new CarreraDAOView(
                        rs.getInt("id_carrera"),
                        rs.getString("codigo_carrera"),
                        rs.getString("nombre_carrera"),
                        rs.getString("facultad"),
                        rs.getString("tipo")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return out;
    }
}
