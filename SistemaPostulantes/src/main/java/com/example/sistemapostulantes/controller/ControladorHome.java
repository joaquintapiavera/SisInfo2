package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorHome extends ControladorBarraSuperior {
    @FXML
    private void postular(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }
}
