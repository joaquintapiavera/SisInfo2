package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.ConexionBaseDatos;
import com.example.sistemapostulantes.model.Estudiante;
import com.example.sistemapostulantes.model.Sesion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorComprobante {

    @FXML private Label lblNombre;
    @FXML private Label lblCI;
    @FXML private Label lblCorreo;
    @FXML private Label lblIdPago;
    @FXML private Label lblFechaPago;
    @FXML private Label lblMonto;
    @FXML private Label lblMetodo;

    @FXML
    public void initialize() {
        try {
            Estudiante e = Sesion.getEstudianteActual();
            lblNombre.setText(e.getNombre() != null ? e.getNombre() : "-");
            lblCI.setText(e.getCi() != null ? e.getCi() : "-");
            lblCorreo.setText(e.getCorreo() != null ? e.getCorreo() : "-");

            try (Connection conn = ConexionBaseDatos.getConexion()) {
                String sql = "SELECT * FROM Pago WHERE id_usuario = ? ORDER BY fecha_pago DESC LIMIT 1";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, e.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            lblIdPago.setText(String.valueOf(rs.getInt("id_pago")));
                            java.sql.Date fecha = rs.getDate("fecha_pago");
                            lblFechaPago.setText(fecha != null ? fecha.toString() : "-");

                            // 👇 corregido: el campo es "monto", no "id_monto"
                            lblMonto.setText(rs.getString("monto"));

                            String metodo = "-";
                            try (PreparedStatement ps2 = conn.prepareStatement(
                                    "SELECT nombre_metodo FROM MetodosPago WHERE id_metodo = ?")) {
                                int idMetodo = rs.getInt("id_metodo");
                                ps2.setInt(1, idMetodo);
                                try (ResultSet rs2 = ps2.executeQuery()) {
                                    if (rs2.next()) metodo = rs2.getString(1);
                                }
                            } catch (Exception ex) {
                                metodo = "ID:" + rs.getInt("id_metodo");
                            }
                            lblMetodo.setText(metodo);
                        } else {
                            lblIdPago.setText("(sin pagos registrados)");
                            lblFechaPago.setText("-");
                            lblMonto.setText("-");
                            lblMetodo.setText("-");
                        }
                    }
                }
            } catch (SQLException ex) {
                lblIdPago.setText("(no se pudo conectar a la base de datos)");
                lblFechaPago.setText("-");
                lblMonto.setText("-");
                lblMetodo.setText("-");
            }

        } catch (IllegalStateException ex) {
            lblNombre.setText("-");
            lblCI.setText("-");
            lblCorreo.setText("-");
            lblIdPago.setText("(no hay usuario en sesión)");
            lblFechaPago.setText("-");
            lblMonto.setText("-");
            lblMetodo.setText("-");
        }
    }

    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}



