package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.ConexionBaseDatos;
import com.example.sistemapostulantes.model.Sesion;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class ControladorPagoTarjeta extends ControladorBarraSuperior {

    @FXML
    private TextField txtTitular;

    @FXML
    private TextField txtNumeroTarjeta;

    @FXML
    private TextField txtMes;

    @FXML
    private TextField txtAnio;

    @FXML
    private PasswordField txtCVV;

    @FXML
    private Button btnPagar;

    private String tokenPago;

    @FXML
    public void initialize() {
        tokenPago = UUID.randomUUID().toString();
        Integer idEstudiante = null;
        try {
            idEstudiante = Sesion.getIdEstudiante();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try (Connection conn = ConexionBaseDatos.getConexion()) {
            String sql = "INSERT INTO Pago (id_usuario, id_metodo, id_monto, fecha_pago, estado, token) VALUES (?, ?, ?, ?, 'pendiente', ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                if (idEstudiante != null) {
                    ps.setInt(1, idEstudiante);
                } else {
                    ps.setNull(1, java.sql.Types.INTEGER);
                }
                ps.setInt(2, 1);
                ps.setDouble(3, 15.0);
                ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                ps.setString(5, tokenPago);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void realizarPago() {
        String titular = txtTitular.getText();
        String numeroTarjeta = txtNumeroTarjeta.getText();
        String mes = txtMes.getText();
        String anio = txtAnio.getText();
        String cvv = txtCVV.getText();

        if (titular.isEmpty() || numeroTarjeta.isEmpty() || mes.isEmpty() || anio.isEmpty() || cvv.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        mostrarAlerta("Pago exitoso", 
            "Titular: " + titular + "\n" +
            "Número: " + numeroTarjeta + "\n" +
            "Fecha: " + mes + "/" + anio + "\n" +
            "CVV: " + cvv);

        try (Connection conn = ConexionBaseDatos.getConexion()) {
            // Buscar el pago pendiente por el token
            String select = "SELECT id_pago FROM Pago WHERE token = ? AND estado = 'pendiente'";
            try (PreparedStatement psSelect = conn.prepareStatement(select)) {
                psSelect.setString(1, tokenPago);
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        int idPago = rs.getInt("id_pago");

                        // Actualizar el estado a pagado
                        String update = "UPDATE Pago SET estado = 'pagado' WHERE id_pago = ?";
                        try (PreparedStatement psUpdate = conn.prepareStatement(update)) {
                            psUpdate.setInt(1, idPago);
                            int updated = psUpdate.executeUpdate();
                            if (updated > 0) {
                                mostrarAlerta("Pago exitoso",
                                        "El pago se ha procesado correctamente.\n" +
                                                "Titular: " + titular + "\n" +
                                                "Número: " + numeroTarjeta + "\n" +
                                                "Fecha: " + mes + "/" + anio);
                            } else {
                                mostrarAlerta("Error", "No se pudo actualizar el estado del pago.");
                            }
                        }
                    } else {
                        mostrarAlerta("Error", "No se encontró un pago pendiente con este token.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Ocurrió un error al procesar el pago: " + e.getMessage());
        }

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

