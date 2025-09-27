package com.example.sistemapostulantes.model;

public class Carrera {

    private int id;

    private String nombre;

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    public Carrera() {
    }


    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
