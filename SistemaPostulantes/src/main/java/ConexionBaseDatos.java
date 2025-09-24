import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBaseDatos {
    private static Connection connection;
    private static final String URL = System.getenv("DB_URL");
    private static final String USUARIO = System.getenv("DB_USER");
    private static final String CONTRASENIA = System.getenv("DB_PASSWORD");

    static {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
