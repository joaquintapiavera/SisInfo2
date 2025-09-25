package com.example.sistemapostulantes.model;

public class Estudiante {
    private int idUsuario;
    private String nombre;
    private String ci;
    private String correo;
    private String direccion;
    private String telefono;
    private int idCarrera;

    
    public Estudiante(int idUsuario, String nombre, String ci, String correo,
                      String direccion, String telefono, int idCarrera) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.ci = ci;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idCarrera = idCarrera;
    }

    
    public int getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public String getCi() { return ci; }
    public String getCorreo() { return correo; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public int getIdCarrera() { return idCarrera; }

    @Override
    public String toString() {
        return "Estudiante{" +
               "idUsuario=" + idUsuario +
               ", nombre='" + nombre + '\'' +
               ", ci='" + ci + '\'' +
               ", correo='" + correo + '\'' +
               ", direccion='" + direccion + '\'' +
               ", telefono='" + telefono + '\'' +
               ", idCarrera=" + idCarrera +
               '}';
    }
}

