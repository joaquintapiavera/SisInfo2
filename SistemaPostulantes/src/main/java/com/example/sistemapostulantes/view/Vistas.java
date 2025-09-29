package com.example.sistemapostulantes.view;

public enum Vistas {
    VISTA_REGISTRO("registro.fxml"),
    VISTA_LOGIN("login.fxml"),
    VISTA_HOME("home.fxml"),
    VISTA_DATOS_PERSONALES("datos-personales.fxml"),
    VISTA_SUBIR_DOCUMENTOS("vista-subir-documentos.fxml"),
    VISTA_CARRERAS("carreras.fxml"),
    VISTA_MI_POSTULACION("mi-postulacion.fxml"),
    VISTA_PAGO_QR("pago-QR.fxml"),
    VISTA_PAGO_TARJETA("pago.fxml");
    
    private final String fxml;

    Vistas(String fxml) {
        this.fxml = fxml;
    }

    public String getVista() {
        return fxml;
    }
}
