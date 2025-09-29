package com.example.sistemapostulantes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ControladorPostulacion extends ControladorBarraSuperior{
    @FXML
    ComboBox opcionPago;

    @FXML
    public void initialize() {
        opcionPago.getItems().addAll("QR", "Tarjeta");
        opcionPago.getSelectionModel().selectFirst();
    }

    @FXML
    public void pagar() {
        opcionPago.getItems().addAll("QR", "Tarjeta");
        opcionPago.getSelectionModel().selectFirst();
    }

}
