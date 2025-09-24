package com.registro;
public class Elemento{
    private int id;
    private String descripcion;
    private String nombre;

    public Elemento(){
    }

    public Elemento(String descripcion, String nombre){
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Elemento(int id, String descripcion, String nombre){
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return "Elemento{id=" + id + ", descripcion='" + descripcion + "', nombre='" + nombre + "'}";
    }
}
