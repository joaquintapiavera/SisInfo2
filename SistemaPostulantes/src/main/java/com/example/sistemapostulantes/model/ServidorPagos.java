package com.example.sistemapostulantes.model;

import com.example.sistemapostulantes.model.ConexionBaseDatos;
import static spark.Spark.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.net.BindException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorPagos {
    private static final int PORT = 4567;
    private static final AtomicBoolean running = new AtomicBoolean(false);

    public static synchronized void iniciarServidor() {
        if (running.get()) return;


        detenerServidor();

        Thread t = new Thread(() -> {
            try {

                if (!PuertoUtils.isPortAvailable(PORT)) {

                    if (!PuertoUtils.waitForPortToBeFree(PORT, 3000)) {
                        System.err.println("Puerto " + PORT + " sigue ocupado. Abortando inicio.");
                        return;
                    }
                }

                port(PORT);

                get("/pagar/:token", (req, res) -> {
                    String token = req.params(":token");
                    String estudianteParam = req.queryParams("estudiante");
                    try (Connection conn = ConexionBaseDatos.getConexion()) {

                        String select = "SELECT id_pago, id_usuario, estado FROM Pago WHERE token = ?";
                        try (PreparedStatement psSelect = conn.prepareStatement(select)) {
                            psSelect.setString(1, token);
                            try (ResultSet rs = psSelect.executeQuery()) {
                                if (!rs.next()) {
                                    res.status(404);
                                    return "Pago no encontrado";
                                }

                                int idPago = rs.getInt("id_pago");
                                Integer idUsuarioActual = rs.getObject("id_usuario") != null ? rs.getInt("id_usuario") : null;

                                if (idUsuarioActual == null && estudianteParam != null) {
                                    try (PreparedStatement psUpdate = conn.prepareStatement(
                                            "UPDATE Pago SET id_usuario = ?, estado = 'pagado' WHERE id_pago = ?")) {
                                        psUpdate.setInt(1, Integer.parseInt(estudianteParam));
                                        psUpdate.setInt(2, idPago);
                                        int updated = psUpdate.executeUpdate();
                                        return updated > 0 ? "Pago asociado y marcado como pagado" : "No se pudo actualizar el pago";
                                    }
                                }

                                try (PreparedStatement psMark = conn.prepareStatement(
                                        "UPDATE Pago SET estado = 'pagado' WHERE id_pago = ?")) {
                                    psMark.setInt(1, idPago);
                                    psMark.executeUpdate();
                                    return "Pago marcado como pagado";
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        res.status(500);
                        return "Error interno: " + e.getMessage();
                    }
                });

                init();


                try {
                    awaitInitialization();
                } catch (Throwable ignored) {

                }


                if (!PuertoUtils.waitForPortToBeFree(PORT, 1000)) {
                    System.out.println("Verificando que el servidor realmente está escuchando en " + PORT);
                }

                running.set(true);
                System.out.println("Servidor SparkJava corriendo en http://localhost:" + PORT);
            } catch (Exception e) {
                System.err.println("BindException: puerto en uso: " + e.getMessage());
                e.printStackTrace();
                running.set(false);
                try { stop(); awaitStopSafely(); } catch (Throwable ignored) {}
            } catch (Throwable th) {
                th.printStackTrace();
                running.set(false);
                try { stop(); awaitStopSafely(); } catch (Throwable ignored) {}
            }
        }, "ServidorPagos-Start");
        t.setDaemon(true);
        t.start();
    }

    public static synchronized void detenerServidor() {
        if (!running.get() && PuertoUtils.isPortAvailable(PORT)) {
            return;
        }

        try {
            stop();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        awaitStopSafely();

        PuertoUtils.waitForPortToBeFree(PORT, 3000);

        running.set(false);
        System.out.println("ServidorPagos detenido (se intentó liberar puerto " + PORT + ")");
    }

    private static void awaitStopSafely() {
        try {
            try {
                awaitStop();
            } catch (Throwable ignored) {}
        } catch (Throwable ignored) {}
    }

    public static boolean isRunning() {
        return running.get();
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(ServidorPagos::detenerServidor));
    }
}
