package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.Estudiante;
import com.example.sistemapostulantes.model.EstudianteCRUD;
import com.example.sistemapostulantes.model.Sesion;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ControladorDatosPersonales extends ControladorBarraSuperior {

    @FXML
    private void confirmarDatos(ActionEvent event) {
        Estudiante est = Sesion.getEstudianteActual();
        if (est == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No hay un estudiante logueado.");
            return;
        }

        try {
            EstudianteCRUD.actualizarEstudiante(est);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Tus datos se actualizaron correctamente.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar tus datos.");
        }
    }

    @FXML
    private void subirFotografía(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

