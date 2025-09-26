package com.example.sistemapostulantes.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public interface CambiadorVista {


    default void cambiarVista(String fxml, ActionEvent event) {
        FXMLLoader loader = loadFXML(fxml);
        Parent root = getRoot(loader);
        Stage stage = getStage(event);
        setupSceneAndShow(stage, root);
    }

    default FXMLLoader loadFXML(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemapostulantes/view/" + fxml));
        try {
            loader.load();
            return loader;
        } catch (IOException exception) {
            throw new RuntimeException("No FXML Found: " + fxml, exception);
        }
    }

    default Parent getRoot(FXMLLoader loader) {
        return loader.getRoot();
    }

    default void initializeController(FXMLLoader loader) {
        Object controller = loader.getController();
    }

    default Stage getStage(ActionEvent event) {
        return (Stage) ((Button) event.getSource()).getScene().getWindow();
    }

    default void setupSceneAndShow(Stage stage, Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/peg_scene.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(650);
        stage.setHeight(650);
        stage.show();
    }
}
