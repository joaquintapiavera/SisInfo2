package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorLogin implements CambiadorVista {
    @FXML
    public void cambiarVistaRegistro(ActionEvent event) {
        cambiarVista(Vistas.VISTA_REGISTRO.getVista(), event);
    }

    @FXML
    public void iniciarSesion(ActionEvent event) {

    }


}
