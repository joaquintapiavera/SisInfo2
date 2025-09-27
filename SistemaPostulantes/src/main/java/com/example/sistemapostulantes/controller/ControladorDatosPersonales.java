package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorDatosPersonales extends ControladorBarraSuperior {
    @FXML
    private void confirmarDatos(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }

    @FXML
    private void subirFotografía(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }
}
