package com.example.sistemapostulantes.model;
import java.time.LocalDateTime;
public class SeleccionCarrera{
    private int estudianteId;
    private int carreraId;
    private LocalDateTime fechaSeleccion;
    public SeleccionCarrera(int estudianteId, int carreraId){
        this.estudianteId = estudianteId;
        this.carreraId = carreraId;
        this.fechaSeleccion = LocalDateTime.now();
    }
    public int getEstudianteId(){ 
        return estudianteId;
    }
    public int getCarreraId(){
         return carreraId;
    }
    public LocalDateTime getFechaSeleccion(){
        return fechaSeleccion;
    }
}