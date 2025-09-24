package com.registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ElementoBD{
    public List<Elemento> obtenerTodos(){
        List<Elemento> lista = new ArrayList<>();
        String sql = "SELECT * FROM elementos ORDER BY id";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Elemento elemento = new Elemento(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getString("nombre")
                );
                lista.add(elemento);
            }
        }catch (SQLException e){
            System.err.println("Error al traer los elementos: " + e.getMessage());
        }
        return lista;
    }

    public boolean existeNombre(String nombre){
        String sql = "SELECT COUNT(*) FROM elementos WHERE nombre = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e){
            System.err.println("Error al verificar si el nombre existe: " + e.getMessage());
        }
        return false;
    }

    public boolean agregarElemento(String nombre){
        if (existeNombre(nombre)){
            return false;
        }
        String descripcion = generarDescripcion();
        String sql = "INSERT INTO elementos (descripcion, nombre) VALUES (?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, descripcion);
            stmt.setString(2, nombre);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e){
            System.err.println("Error al guardar el elemento: " + e.getMessage());
            return false;
        }
    }

    private String generarDescripcion(){
        String sql = "SELECT COUNT(*) FROM elementos";
        try (Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            if (rs.next()){
                int cantidad = rs.getInt(1);
                if (cantidad == 0){
                    return "base";
                } else if (cantidad == 1){
                    return "x2";
                } else {
                    return "x" + (cantidad + 1);
                }
            }
        } catch (SQLException e){
            System.err.println("Error al generar la descripción: " + e.getMessage());
        }
        return "x2";
    }

}
