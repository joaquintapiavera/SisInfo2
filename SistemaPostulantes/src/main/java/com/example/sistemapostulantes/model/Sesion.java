package com.example.sistemapostulantes.model;

public class Sesion {
    private static Estudiante estudianteActual;

    public static void setEstudianteActual(Estudiante estudiante) {
        estudianteActual = estudiante;
    }

    public static Estudiante getEstudianteActual() {
        if (estudianteActual == null) {
            throw new IllegalStateException("No hay un estudiante en sesión.");
        }
        return estudianteActual;
    }

    public static int getIdEstudiante() {
        if (estudianteActual == null) {
            throw new IllegalStateException("No hay un estudiante en sesión.");
        }
        Integer id = estudianteActual.getId();
        if (id == null || id <= 0) {
            throw new IllegalStateException("El ID del estudiante en sesión no es válido.");
        }
        return id;
    }

    public static void cerrarSesion() {
        estudianteActual = null;
    }
}



