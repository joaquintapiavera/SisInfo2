package com.example.sistemapostulantes.model;

public class Estudiante {
    
    private Integer id;
    private String nombre;
    private String ci;
    private String correo;
    private String direccion;
    private String telefono;
    private int idCarrera;
    private String contrasenia;

    public Estudiante() { }

    
    public Estudiante(String nombre, String ci, String correo, String direccion, String telefono, int idCarrera, String contrasenia) {
        this.nombre = nombre;
        this.ci = ci;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idCarrera = idCarrera;
        this.contrasenia = contrasenia;
    }

   
    public Estudiante(Integer id, String nombre, String ci, String correo, String direccion, String telefono, int idCarrera, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.ci = ci;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idCarrera = idCarrera;
        this.contrasenia = contrasenia;
    }

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getIdCarrera() { return idCarrera; }
    public void setIdCarrera(int idCarrera) { this.idCarrera = idCarrera; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
}


