package com.example.sistemapostulantes.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public interface CambiadorVista {

    default void cambiarVista(String fxml, ActionEvent event) {
        FXMLLoader loader = loadFXML(fxml);
        Parent root = loader.getRoot();
        Stage stage = getStage(event);
        setupSceneAndShow(stage, root);
    }

    default FXMLLoader loadFXML(String fxml) {
        String path = "/com/example/sistemapostulantes/" + fxml;
        URL url = getClass().getResource(path);
        if (url == null) throw new RuntimeException("FXML no encontrado: " + path);
        FXMLLoader loader = new FXMLLoader(url);
        try {
            loader.load();
            return loader;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default Parent getRoot(FXMLLoader loader) {
        return loader.getRoot();
    }

    default Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    default void setupSceneAndShow(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.show();
    }
}
