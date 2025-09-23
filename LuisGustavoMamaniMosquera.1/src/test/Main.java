/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection con = Conexion.getConexion();

        if (con != null) {
            try {
                Scanner sc = new Scanner(System.in);

                System.out.print("Ingrese el nombre del producto: ");
                String nombre = sc.nextLine();

                System.out.print("Ingrese el precio: ");
                double precio = sc.nextDouble();

                // 1. Verificar si ya existe el producto
                String verificarSQL = "SELECT COUNT(*) FROM reposteria WHERE nombre = ?";
                PreparedStatement psVerificar = con.prepareStatement(verificarSQL);
                psVerificar.setString(1, nombre);
                ResultSet rs = psVerificar.executeQuery();
                rs.next();
                int existe = rs.getInt(1);

                if (existe > 0) {
                    System.out.println("⚠️ El producto ya existe en la repostería.");
                } else {
                    // 2. Insertar si no existe
                    String sql = "INSERT INTO reposteria (nombre, precio) VALUES (?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, nombre);
                    ps.setDouble(2, precio);

                    int filas = ps.executeUpdate();

                    if (filas > 0) {
                        System.out.println("✅ Producto añadido con éxito.");
                    }
                }

                // 3. Mostrar todos los productos
                System.out.println("\n📋 Lista de productos en repostería:");
                Statement st = con.createStatement();
                ResultSet rsLista = st.executeQuery("SELECT * FROM reposteria");

                while (rsLista.next()) {
                    int id = rsLista.getInt("id");
                    String nom = rsLista.getString("nombre");
                    double pre = rsLista.getDouble("precio");
                    System.out.println(id + " | " + nom + " | $" + pre);
                }

                con.close();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}

   
