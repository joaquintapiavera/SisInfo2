package com.example.sistemapostulantes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarrerasDAO {


    public static Carrera obtenerCarreraPorId(int idCarrera) {
        Carrera carrera = null;


        String sql = "SELECT id_carrera, nombre_carrera FROM Carrera WHERE id_carrera = ?";

        try (Connection con = ConexionBaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setInt(1, idCarrera);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                carrera = new Carrera();


                carrera.setId(rs.getInt("id_carrera"));
                carrera.setNombre(rs.getString("nombre_carrera"));
            }

        } catch (SQLException e) {

            System.err.println("Error al obtener la carrera por ID:");
            e.printStackTrace();
        }

        return carrera;
    }
}
