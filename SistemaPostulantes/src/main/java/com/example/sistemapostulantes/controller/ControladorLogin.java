package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.Estudiante;
import com.example.sistemapostulantes.model.EstudianteDAO;
import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorLogin implements CambiadorVista {

    @FXML
    private TextField ci;
    @FXML
    private PasswordField contrasenia;

    @FXML
    public void cambiarVistaRegistro(ActionEvent event) {
        cambiarVista(Vistas.VISTA_REGISTRO.getVista(), event);
    }

    @FXML
    public void iniciarSesion(ActionEvent event) {
        Estudiante estudiante = new Estudiante("","","","", "", 0, "");
        try{
            estudiante = EstudianteDAO.obtenerPorCI(ci.getText());
        } catch (Exception e){
            System.out.println("Error al iniciar Sesion");
        }

        System.out.println(estudiante.getContrasenia());

    }


}
