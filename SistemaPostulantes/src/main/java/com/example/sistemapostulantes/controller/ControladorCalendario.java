package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.ServidorPagos;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ControladorCalendario extends ControladorBarraSuperior{
    @FXML
    private ComboBox seleccionFacultad;

    @FXML
    private ImageView calendarioImagen;

    @FXML
    public void initialize() {
        seleccionFacultad.getItems().addAll("FCYT", "FCE", "FACH");
        seleccionFacultad.getSelectionModel().selectFirst();
    }

    @FXML
    public void filtrar() {
        String opcionSeleccionada = (String) seleccionFacultad.getValue();
        if (opcionSeleccionada == null) {
            System.out.println("No se seleccionó la facultad");
            return;
        }
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/com/example/sistemapostulantes/imagenes/calendario_fcyt.jpg")).toExternalForm());
        switch (opcionSeleccionada) {
            case "FCYT":
                calendarioImagen.setImage(image);
                break;
            case "FCE":
                image = new Image(Objects.requireNonNull(getClass().getResource("/com/example/sistemapostulantes/imagenes/calendario_fce.jpg")).toExternalForm());
                calendarioImagen.setImage(image);
                break;
            case "FACH":
                image = new Image(Objects.requireNonNull(getClass().getResource("/com/example/sistemapostulantes/imagenes/calendario_fach.jpg")).toExternalForm());
                calendarioImagen.setImage(image);
            default:
                System.out.println("Opción desconocida: " + opcionSeleccionada);
            break;
        }
    }

}
