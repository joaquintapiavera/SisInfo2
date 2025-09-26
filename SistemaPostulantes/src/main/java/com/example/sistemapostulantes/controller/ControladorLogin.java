package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.Estudiante;
import com.example.sistemapostulantes.model.EstudianteDAO;
import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorLogin implements CambiadorVista {

    @FXML
    private TextField ci;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private void iniciarSesion(ActionEvent event) {
        if (!validarCampos()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor ingresa tu CI y contraseña.");
            return;
        }
        try {
            Estudiante estudiante = EstudianteDAO.obtenerPorCI(ci.getText());
            if (estudiante != null && estudiante.getContrasenia().equals(contrasenia.getText())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Login Correcto", "Bienvenido " + estudiante.getNombre());
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Login Incorrecto", "Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al iniciar sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean validarCampos() {
        return !ci.getText().isEmpty() && !contrasenia.getText().isEmpty();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void cambiarVistaRegistro(ActionEvent event) {
        cambiarVista(Vistas.VISTA_REGISTRO.getVista(), event);
    }

    @FXML
    private void abrirCarreras(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemapostulantes/carreras.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Carreras");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la vista de carreras.");
            e.printStackTrace();
        }
    }
}

