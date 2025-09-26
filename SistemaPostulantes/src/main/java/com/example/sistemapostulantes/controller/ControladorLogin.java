package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.Estudiante;
import com.example.sistemapostulantes.model.EstudianteDAO;
import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorLogin implements CambiadorVista {

    @FXML
    private TextField ci;
    @FXML
    private PasswordField contrasenia;

    @FXML
    public void cambiarVistaRegistro(ActionEvent event) {
        cambiarVista(Vistas.VISTA_REGISTRO.getVista(), event);
    }

    @FXML
    public void iniciarSesion(ActionEvent event) {
        Estudiante estudiante;
        try {
            estudiante = EstudianteDAO.obtenerPorCI(ci.getText());

            if (estudiante != null && estudiante.getContrasenia().equals(contrasenia.getText())) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Login Correcto", "Bienvenido " + estudiante.getNombre());
                
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Login Incorrecto", "Usuario o contraseña incorrectos.");
            }

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", " Ocurrió un error al iniciar sesión.");
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
