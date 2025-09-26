package com.example.sistemapostulantes.view;

public enum Vistas {
    VISTA_MENU("vista-menu.fxml"),
    VISTA_LOGIN("vista-login.fxml"),
    HOME("home.fxml");

    private String fxml;

    private Vistas(String fxml) {
        this.fxml = fxml;
    }

    public String getVista() {
        return fxml;
    }
}
