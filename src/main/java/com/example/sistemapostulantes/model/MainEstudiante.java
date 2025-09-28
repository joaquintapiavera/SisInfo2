
package com.example.sistemapostulantes.model;

import java.util.Scanner;

public class MainEstudiante {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el CI del estudiante a buscar: ");
        String ciBuscado = sc.nextLine(); 

        Estudiante est = EstudianteDAO.obtenerPorCI(ciBuscado);

        if (est != null) {
            System.out.println("Estudiante encontrado:");
            System.out.println(est);
        } else {
            System.out.println("No se encontró ningún estudiante con CI: " + ciBuscado);
        }

        sc.close();
    }
}
