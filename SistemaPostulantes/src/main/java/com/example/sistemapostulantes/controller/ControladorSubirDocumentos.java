package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorSubirDocumentos extends ControladorBarraSuperior{
    @FXML
    private void enviarDatos(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }

    @FXML
    private void subirCi(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }

    @FXML
    private void subirDiploma(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }

    @FXML
    private void subirCertificadoNacimiento(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }

    @FXML
    private void subirFotografía(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }
}
