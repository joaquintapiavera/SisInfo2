/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // URL de conexión con ajustes:
    // - useSSL=false → desactiva SSL
    // - allowPublicKeyRetrieval=true → permite recuperar la clave pública
    // - serverTimezone=UTC → evita problemas de zona horaria
    private static final String URL = "jdbc:mysql://localhost:3306/pasteleria?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "gustavo2464M"; // tu contraseña de Workbench

    public static Connection getConexion() {
        Connection con = null;
        try {
            // Cargar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error en la conexión: " + e.getMessage());
        }
        return con;
    }
}



    
