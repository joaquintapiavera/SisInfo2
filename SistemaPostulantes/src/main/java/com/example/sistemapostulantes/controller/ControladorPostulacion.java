package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.ServidorPagos;
import com.example.sistemapostulantes.view.Vistas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ControladorPostulacion extends ControladorBarraSuperior{
    @FXML
    ComboBox opcionPago;

    @FXML
    public void initialize() {
        opcionPago.getItems().addAll("QR", "Tarjeta");
        opcionPago.getSelectionModel().selectFirst();


        new Thread(() -> ServidorPagos.detenerServidor()).start();
    }

    @FXML
    public void pagar(ActionEvent event) {
        String opcionSeleccionada = (String) opcionPago.getValue();

        if (opcionSeleccionada == null) {
            System.out.println("No se seleccionó ningún método de pago");
            return;
        }

        switch (opcionSeleccionada) {
            case "QR":
                cambiarVista(Vistas.VISTA_PAGO_QR.getVista(), event);
                break;
            case "Tarjeta":
                cambiarVista(Vistas.VISTA_PAGO_TARJETA.getVista(), event);

                break;
            default:
                System.out.println("Opción desconocida: " + opcionSeleccionada);
        }
        new Thread(() -> com.example.sistemapostulantes.model.ServidorPagos.iniciarServidor()).start();

    }
    @FXML
    public void verComprobante(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemapostulantes/comprobante.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Comprobante de Pago");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
