package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.DocumentosCRUD;
import com.example.sistemapostulantes.model.Sesion;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

public class ControladorSubirDocumentos extends ControladorBarraSuperior {

    private FileChooser fileChooser = new FileChooser();

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

    private File seleccionarArchivo(String tipo) {
        try {
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"),
                    new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png")
            );
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String nameLower = file.getName().toLowerCase();
            
                if (nameLower.endsWith(".pdf")) {
                    long maxBytes = 5L * 1024L * 1024L;
                    if (file.length() > maxBytes) {
                        double sizeMB = file.length() / (1024.0 * 1024.0);
                        mostrarAlerta(Alert.AlertType.ERROR, "Archivo demasiado grande", 
                            String.format("El PDF pesa %.2f MB, debe ser menor a 5 MB", sizeMB));
                        return null;
                    }
                
                    try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
                        byte[] header = new byte[4];
                        int read = fis.read(header);
                        String headerStr = (read == 4) ? new String(header, "ISO-8859-1") : "";
                        if (!headerStr.startsWith("%PDF")) {
                            mostrarAlerta(Alert.AlertType.ERROR, "Archivo inválido", 
                                "El archivo seleccionado no es un PDF válido");
                            return null;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        mostrarAlerta(Alert.AlertType.ERROR, "Error", 
                            "No se pudo validar el archivo: " + ex.getMessage());
                        return null;
                    }
                }
                mostrarAlerta(Alert.AlertType.INFORMATION, "Archivo seleccionado", 
                    tipo + " cargado correctamente");
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", 
                "No se pudo seleccionar el archivo: " + tipo);
            return null;
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
