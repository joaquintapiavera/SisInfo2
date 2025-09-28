package com.example.sistemapostulantes.model;

public class Estudiante {
    private String nombre;
    private String ci;
    private String correo;
    private String direccion;
    private String telefono;
    private int idCarrera;
    private String contrasenia;

    
    public Estudiante(String nombre, String ci, String correo,
                      String direccion, String telefono, int idCarrera, String contrasenia) {
        this.nombre = nombre;
        this.ci = ci;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idCarrera = idCarrera;
        this.contrasenia = contrasenia;
    }


    public String getNombre() { return nombre; }
    public String getCi() { return ci; }
    public String getCorreo() { return correo; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public int getIdCarrera() { return idCarrera; }
    public String getContrasenia() { return contrasenia; }

    @Override
    public String toString() {
        return "Estudiante{" +
               ", nombre='" + nombre + '\'' +
               ", ci='" + ci + '\'' +
               ", correo='" + correo + '\'' +
               ", direccion='" + direccion + '\'' +
               ", telefono='" + telefono + '\'' +
               ", idCarrera=" + idCarrera +
               '}';
    }
}

