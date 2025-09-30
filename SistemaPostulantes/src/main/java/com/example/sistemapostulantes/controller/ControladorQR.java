package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.ConexionBaseDatos;
import com.example.sistemapostulantes.model.Sesion;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.MultiFormatWriter;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.UUID;

public class ControladorQR extends ControladorBarraSuperior {
    @FXML
    private ImageView qr;

    private String tokenPago;

    @FXML
    public void generarQR() {
        try {
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
                    ps.setInt(2, 1); // Método de pago, por ejemplo 1 = tarjeta
                    ps.setDouble(3, 15.0); // Monto fijo, cambia según necesites
                    ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                    ps.setString(5, tokenPago);
                    ps.executeUpdate();
                }
            }

            String url = "http://localhost:4567/pagar/" + tokenPago;

            int ancho = 275;
            int alto = 275;
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(url, BarcodeFormat.QR_CODE, ancho, alto);

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            qr.setImage(fxImage);

            System.out.println("QR generado. Token: " + tokenPago);
            System.out.println("URL: " + url);

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void verificarPago() {
        if (tokenPago == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("No se ha generado ningún QR aún.");
            alerta.showAndWait();
            return;
        }

        try (Connection conn = ConexionBaseDatos.getConexion()) {
            String sql = "SELECT estado FROM Pago WHERE token = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, tokenPago);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    String estado = rs.getString("estado");
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Estado del Pago");
                    alerta.setHeaderText(null);
                    alerta.setContentText("El pago está: " + estado);
                    alerta.showAndWait();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error");
                    alerta.setHeaderText(null);
                    alerta.setContentText("No se encontró el pago en la base de datos.");
                    alerta.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Ocurrió un error al verificar el pago.");
            alerta.showAndWait();
        }
    }
}
