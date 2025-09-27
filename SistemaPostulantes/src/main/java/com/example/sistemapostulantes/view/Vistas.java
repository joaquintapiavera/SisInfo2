package com.example.sistemapostulantes.view;

public enum Vistas {
    VISTA_REGISTRO("registro.fxml"),
    VISTA_LOGIN("login.fxml"),
    VISTA_HOME("home.fxml"),
    VISTA_DATOS_PERSONALES("datos-personales.fxml"),
    VISTA_SUBIR_DOCUMENTOS("vista-subir-documentos.fxml"),;


    private String fxml;

    private Vistas(String fxml) {
        this.fxml = fxml;
    }

    public String getVista() {
        return fxml;
    }
}
