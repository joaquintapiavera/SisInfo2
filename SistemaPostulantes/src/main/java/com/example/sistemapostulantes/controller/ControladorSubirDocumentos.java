package com.example.sistemapostulantes.controller;

import java.io.File;

import com.example.sistemapostulantes.model.DocumentosCRUD;
import com.example.sistemapostulantes.model.Sesion;
import com.example.sistemapostulantes.view.Vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

public class ControladorSubirDocumentos extends ControladorBarraSuperior {

    private FileChooser fileChooser = new FileChooser();
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    private File ciFile;
    private File diplomaFile;
    private File fotoFile;
    private File certNacFile;

    @FXML
    private void enviarDatos(ActionEvent event) {
        try {
            int idUsuario = Sesion.getIdEstudiante(); // lanza excepción si no hay sesión activa

            DocumentosCRUD.guardarDocumentos(
                    idUsuario,
                    ciFile, diplomaFile, fotoFile, certNacFile
            );

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Todos los documentos fueron guardados correctamente.");
            cambiarVista(Vistas.VISTA_DATOS_PERSONALES.getVista(), event);

        } catch (IllegalStateException e) {
            
            mostrarAlerta(Alert.AlertType.ERROR, "Error de sesión", e.getMessage());

        } catch (Exception e) {
            
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudieron guardar los documentos: " + e.getMessage());
        }
    }

    @FXML
    private void subirCi(ActionEvent event) {
        ciFile = seleccionarArchivo("CI");
    }

    @FXML
    private void subirDiploma(ActionEvent event) {
        diplomaFile = seleccionarArchivo("Diploma");
    }

    @FXML
    private void subirFotografía(ActionEvent event) {
        fotoFile = seleccionarArchivo("Fotografía");
    }

    @FXML
    private void subirCertificadoNacimiento(ActionEvent event) {
        certNacFile = seleccionarArchivo("Certificado de Nacimiento");
    }

    private File seleccionarArchivo(String tipo){
        try{
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg", "*.jpeg", "*.png")
            );

            File file = fileChooser.showOpenDialog(null);
            if (file != null){
                String nombre = file.getName().toLowerCase();
                if (nombre.endsWith(".pdf")) {
                    if (file.length() > MAX_FILE_SIZE) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Tamaño inválido",
                                "El archivo debe pesar menos de 5 MB.");
                        return null;
                    }
                    if (!esPdfReal(file)) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Archivo no válido",
                                "El archivo no parece ser un PDF válido.");
                        return null;
                    }
                }
                mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Archivo seleccionado", tipo + " cargado correctamente.");
            }
            return file;
        }catch (Exception e){
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo seleccionar el archivo: " + tipo);
            return null;
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private boolean esPdfReal(File file){
        try(java.io.FileInputStream fis = new java.io.FileInputStream(file)){
            byte[] header = new byte[5];
            int n = fis.read(header);
            if (n < 4) return false;
            String h = new String(header, "ISO-8859-1");
            return h.startsWith("%PDF");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
}
