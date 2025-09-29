package com.example.sistemapostulantes.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;

public class ControladorCalendario {

    @FXML
    private AnchorPane rootPane; // Corresponde al fx:id="rootPane" en el FXML

    // Dentro de la clase ControladorCalendario
// ...
    @FXML
    public void initialize() {
        // RUTA DE LA IMAGEN: ¡VERIFICA QUE COINCIDA CON EL NOMBRE DEL ARCHIVO!
        String imagePath = "/com/example/sistemapostulantes/imagenes/calendario_uni.jpg";

        try {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl == null) {
                System.err.println("Error: No se encontró la imagen en la ruta: " + imagePath);
                return;
            }

            Image calendarioImage = new Image(imageUrl.toExternalForm());
            ImageView imageView = new ImageView(calendarioImage);

            // Escalar la imagen para que se ajuste al tamaño del AnchorPane
            imageView.fitWidthProperty().bind(rootPane.widthProperty());
            imageView.fitHeightProperty().bind(rootPane.heightProperty());

            // Agregar la imagen al panel
            rootPane.getChildren().add(imageView);
            AnchorPane.setTopAnchor(imageView, 0.0);
            AnchorPane.setBottomAnchor(imageView, 0.0);
            AnchorPane.setLeftAnchor(imageView, 0.0);
            AnchorPane.setRightAnchor(imageView, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Excepción al cargar y mostrar la imagen del calendario.");
        }
    }
}
