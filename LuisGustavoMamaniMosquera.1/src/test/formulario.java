/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class formulario extends JFrame {
    private JTextField txtNombre;
    private JButton btnAgregar;

    public formulario() {
        setTitle("Repostería");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Etiqueta y campo de texto
        add(new JLabel("Nombre del postre:"));
        txtNombre = new JTextField(20);
        add(txtNombre);

        // Botón para añadir
        btnAgregar = new JButton("Añadir postre");
        add(btnAgregar);

        // Acción del botón
        btnAgregar.addActionListener(e -> agregarProducto());
    }

    private void agregarProducto() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, escriba un nombre.");
            return;
        }

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
                return;
            }

            // Verificar si ya existe
            String verificarSQL = "SELECT COUNT(*) FROM reposteria WHERE nombre = ?";
            try (PreparedStatement psVerificar = con.prepareStatement(verificarSQL)) {
                psVerificar.setString(1, nombre);
                try (ResultSet rs = psVerificar.executeQuery()) {
                    rs.next();
                    if (rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(this, "El postre ya existe en la base de datos.");
                        return;
                    }
                }
            }

            // Insertar postre
            String sql = "INSERT INTO reposteria (nombre) VALUES (?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Postre añadido con éxito.");
                txtNombre.setText(""); // limpiar campo
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error en SQL: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new formulario().setVisible(true));
    }
}




