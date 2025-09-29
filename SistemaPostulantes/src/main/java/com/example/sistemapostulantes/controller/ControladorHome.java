package com.example.sistemapostulantes.controller;

// Asegúrate de que todas estas importaciones existan al inicio del archivo
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
// ... otras importaciones de tu proyecto ...


public class ControladorHome extends ControladorBarraSuperior {
    // ... tus otros atributos y métodos...

    @FXML
    private void postular(ActionEvent event) {
        // ... (Tu código para cambiar de vista)
    }

    @FXML
    private void ircarreras(ActionEvent event) {
        // ... (Tu código para cambiar de vista)
    }

    @FXML // <--- ¡Importante! El onAction en home.fxml lo necesita
    public void abrirCalendario(ActionEvent event) {
        try {
            // Carga el FXML de calendario (ruta absoluta desde la raíz de resources)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemapostulantes/calendario.fxml"));
            Parent root = loader.load();

            // Crea la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Calendario Académico");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la vista del calendario. Verifique la ruta del FXML.");
        }
    }
}
