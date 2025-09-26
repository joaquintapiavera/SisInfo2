package com.example.sistemapostulantes.view;

public enum Vistas {
    VISTA_REGISTRO("registro.fxml"),
    VISTA_LOGIN("login.fxml"),
    HOME("home.fxml");

    private String fxml;

    private Vistas(String fxml) {
        this.fxml = fxml;
    }

    public String getVista() {
        return fxml;
    }
}
