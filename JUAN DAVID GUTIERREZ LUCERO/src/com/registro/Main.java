package com.registro;
import javax.swing.SwingUtilities;
public class Main{
    public static void main(String[] args){
        System.out.println("=== ARRANCANDO PROGRAMA ===");
        System.out.println("Probando conexión con la base de datos...");
        ConexionBD.probarConexion();
        SwingUtilities.invokeLater(() -> {
            try{
                System.out.println("Mostrando ventana principal...");
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
                System.out.println("Aplicación iniciada con éxito.");
            }catch (Exception e){
                System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
