package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.Estudiante;
import com.example.sistemapostulantes.model.EstudianteCRUD;
import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ControladorRegistro implements CambiadorVista {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField ciField;
    @FXML
    private TextField correoField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField contraseniaField;

    @FXML
    public void VistaLogin(ActionEvent event) {
        cambiarVista(Vistas.VISTA_LOGIN.getVista(), event);
    }

    @FXML
    public void RegistrarUsuario(ActionEvent event) {
        Estudiante estudiante = new Estudiante(
                nombreField.getText(),
                ciField.getText(),
                correoField.getText(),
                direccionField.getText(),
                telefonoField.getText(),
                1,
                contraseniaField.getText()
        );

        try {
            EstudianteCRUD.crearEstudiante(estudiante);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", " El usuario ha sido registrado correctamente.");
            
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Fallo en el Registro", "No se pudo registrar .");
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

