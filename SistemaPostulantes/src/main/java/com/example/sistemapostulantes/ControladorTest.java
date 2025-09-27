package com.example.sistemapostulantes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorTest {

    @FXML
    private TextField nombreField, correoField, direccionField, telefonoField;

    @FXML
    private PasswordField contrasenaField;

    @FXML
    private void onRegisterButtonClick(ActionEvent event) {
        System.out.println("===== Datos del Usuario =====");
        System.out.println("Nombre: " + nombreField.getText());
        System.out.println("Correo: " + correoField.getText());
        System.out.println("Dirección: " + direccionField.getText());
        System.out.println("Teléfono: " + telefonoField.getText());
        System.out.println("Contraseña: " + contrasenaField.getText());
        System.out.println("=============================");
    }
}






