package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class ControladorBarraSuperior implements CambiadorVista {
    @FXML
    public void cerrarSesion(ActionEvent event) {
        cambiarVista(Vistas.VISTA_LOGIN.getVista(), event);
    }

    @FXML
    public void cambiarVistaAHome(ActionEvent event) {
        cambiarVista(Vistas.VISTA_HOME.getVista(), event);
    }

    @FXML
    public void verCarreras(ActionEvent event) {
        cambiarVista(Vistas.VISTA_LOGIN.getVista(), event);
    }

    @FXML
    public void verMiPostulacion(ActionEvent event) {
        cambiarVista(Vistas.VISTA_LOGIN.getVista(), event);
    }

    @FXML
    public void verMiPerfil(ActionEvent event) {
        cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);
    }
}
