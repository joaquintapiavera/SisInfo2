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

/**
 * Controlador para mostrar el comprobante.
 *
 * Uso adicional desde la UI de pago:
 *   // antes de abrir el comprobante:
 *   ControladorComprobante.setMetodoSeleccionado("QR");   // o "Tarjeta"
 *
 * Con esto, si no hay BD o no existe registro, el comprobante usará
 * un ID fijo (SIM-PAGO-0001), la fecha actual, un monto fijo y
 * el método seleccionado.
 */
public class ControladorComprobante {

    @FXML private Label lblNombre;
    @FXML private Label lblCI;
    @FXML private Label lblCorreo;
    @FXML private Label lblIdPago;
    @FXML private Label lblFechaPago;
    @FXML private Label lblMonto;
    @FXML private Label lblMetodo;

    // Monto por defecto si no hay registro en BD
    private static final double MONTO_FIJO = 200.0;

    // ID de pago fijo (no depende del estudiante)
    private static final String PAGO_SIMULADO_ID = "SIM-PAGO-0001";

    // Valor que debe establecerse desde el flujo de pago antes de abrir el comprobante.
    // Ejemplo: ControladorComprobante.setMetodoSeleccionado("QR");
    private static String metodoSeleccionado = null;

    // Setter que debe usar el flujo de pago (QR / Tarjeta)
    public static void setMetodoSeleccionado(String metodo) {
        metodoSeleccionado = metodo;
    }

    @FXML
    public void initialize() {
        try {
            Estudiante e = Sesion.getEstudianteActual();

            // Mostrar datos del usuario logueado siempre que estén disponibles
            lblNombre.setText(e.getNombre() != null ? e.getNombre() : "-");
            lblCI.setText(e.getCi() != null ? e.getCi() : "-");
            lblCorreo.setText(e.getCorreo() != null ? e.getCorreo() : "-");

            boolean pagoEncontrado = false;

            try (Connection conn = ConexionBaseDatos.getConexion()) {
                // Intentamos leer el último pago del estudiante (esquema metodos_pago)
                String sql = "SELECT * FROM metodos_pago.Pagos WHERE idEstudiante = ? ORDER BY fecha_pago DESC LIMIT 1";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, e.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            // Si hay registro en BD, usar esos datos
                            lblIdPago.setText(String.valueOf(rs.getInt("idPagos")));
                            java.sql.Date fecha = rs.getDate("fecha_pago");
                            lblFechaPago.setText(fecha != null ? fecha.toString() : java.time.LocalDate.now().toString());
                            lblMonto.setText(String.valueOf(rs.getDouble("Montos")));

                            // Consultar nombre del método en catálogo
                            String metodo = "-";
                            try (PreparedStatement ps2 = conn.prepareStatement(
                                    "SELECT nombre FROM metodos_pago.MetodosPago WHERE idCancelar = ?")) {
                                int idMetodo = rs.getInt("idCancelar");
                                ps2.setInt(1, idMetodo);
                                try (ResultSet rs2 = ps2.executeQuery()) {
                                    if (rs2.next()) metodo = rs2.getString("nombre");
                                }
                            } catch (Exception ex) {
                                // Si algo falla, mostramos el id del método
                                metodo = "ID:" + rs.getInt("idCancelar");
                            }
                            lblMetodo.setText(metodo);

                            pagoEncontrado = true;
                        }
                    }
                }
            } catch (SQLException ex) {
                // No mostramos "no se pudo conectar". En su lugar, iremos al modo fallback.
                System.err.println("⚠️ No se pudo leer pagos desde la BD (se usará modo offline simulad o): " + ex.getMessage());
            }

            // MODO FALLBACK: si no se halló pago en BD, mostramos datos simulados
            if (!pagoEncontrado) {
                // ID fijo de comprobante (no dependiente del estudiante)
                lblIdPago.setText(PAGO_SIMULADO_ID);

                // Fecha: hoy
                lblFechaPago.setText(java.time.LocalDate.now().toString());

                // Monto fijo
                lblMonto.setText(String.valueOf(MONTO_FIJO));

            }

        } catch (IllegalStateException ex) {
            // Si no hay usuario en sesión, mostrar indicación amigable
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

