package com.registro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionBD{
    private static final String URL_BD = "jdbc:mysql://avnadmin:AVNS_l0YRyO-laQz0uaAmDF2@mysql-sisinfo2.d.aivencloud.com:11329/Registro?ssl-mode=REQUIRED";
    private static final String USUARIO_BD = "root";
    private static final String CLAVE_BD = "AVNS_l0YRyO-laQz0uaAmDF2";
    
    public static Connection obtenerConexion() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL_BD, USUARIO_BD, CLAVE_BD);
        }catch (ClassNotFoundException e){
            throw new SQLException("No se encontró el driver de MySQL", e);
        }
    }
    public static void probarConexion(){
        try (Connection conn = obtenerConexion()){
            System.out.println("Conexión establecida con la base de datos.");
        }catch (SQLException e){
            System.err.println("No se pudo conectar a la base de datos: " + e.getMessage());
        }
    }
}
