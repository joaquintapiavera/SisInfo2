package com.example.sistemapostulantes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControladorPago extends ControladorBarraSuperior {

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
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

