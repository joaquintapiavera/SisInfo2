package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorMenu implements CambiadorVista {
    @FXML
    public void VistaRegistro(ActionEvent event) {
        cambiarVista(Vistas.VISTA_MENU.getVista(), event);
    }
}
